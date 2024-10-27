package de.grewdev.utils.manager;

import de.grewdev.embeds.NextLvlEmbed;
import de.grewdev.utils.DatabaseConnection;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Random;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class LvlSystemManager {

    private static final Logger logger = LoggerFactory.getLogger(LvlSystemManager.class);
    private static LvlSystemManager instance;
    private final JDA jda;
    private final DSLContext dBase;

    private LvlSystemManager(JDA jda) {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        this.dBase = DSL.using(connection, SQLDialect.MYSQL);
        this.jda = jda;

        createIfNotExistsEmbedTable("lvlSystem");
    }

    private void createIfNotExistsEmbedTable(String tableName) {
        dBase.createTableIfNotExists(tableName)
                .column("memberId", VARCHAR(36))
                .column("lastMemberName", VARCHAR(50))
                .column("memberTag", VARCHAR(36))
                .column("memberAvatarUrl", VARCHAR(512))
                .column("xp", INTEGER.notNull().defaultValue(0))
                .column("lastUpdate", TIMESTAMP.notNull().defaultValue(DSL.currentTimestamp()))
                .column("lvl", INTEGER.notNull().defaultValue(0))
                .primaryKey("memberId")
                .execute();
    }

    public static LvlSystemManager getInstance(JDA jda) {
        if (instance == null) {
            instance = new LvlSystemManager(jda);
        }
        return instance;
    }

    public boolean isTimeOut(User user) {

        Record3<Integer, Timestamp, Integer> dbUser = getUser(user);
        if (dbUser == null) return false;

        Timestamp lastupdate = dbUser.value2();
        Timestamp curTime = dBase.fetchValue(select(currentTimestamp()));

        if (System.getenv("LVLSYS_TIMEOUT") == null) return true;
        int timeOutDif = Integer.parseInt(System.getenv("LVLSYS_TIMEOUT"));
        long diff = (curTime.getTime() - lastupdate.getTime()) / 1000;

        return !(diff > timeOutDif);
    }

    private Record3<Integer, Timestamp, Integer> getUser(User user) {
        Result<Record3<Integer, Timestamp, Integer>> result = dBase.select(
                        field("xp", INTEGER),
                        field("lastUpdate", Timestamp.class),
                        field("lvl", INTEGER)
                )
                .from(table("lvlSystem"))
                .where(field("memberId", String.class).eq(String.valueOf(user.getIdLong())))
                .fetch();

        if (result.isEmpty()) return null;

        return result.getFirst();
    }

    private void setData(Long memberId, String lastMemberName, String memberTag, String memberAvatrUrl,  Integer xp, Timestamp lastUpdate, Integer lvl){
        dBase.insertInto(table("lvlSystem"))
                .columns(field("memberId"), field("lastMemberName"), field("memberTag"), field("memberAvatrUrl"), field("xp"), field("lastUpdate"), field("lvl"))
                .values(memberId, lastMemberName, memberTag, memberAvatrUrl, xp, lastUpdate, lvl)
                .onDuplicateKeyUpdate()
                .set(field("lastMemberName"), lastMemberName)
                .set(field("memberTag"), memberTag)
                .set(field("memberAvatrUrl"), memberAvatrUrl)
                .set(field("xp"), xp)
                .set(field("lastUpdate"), lastUpdate)
                .set(field("lvl"), lvl)
                .execute();
    }

    private int randomeXP() {
        if (System.getenv("LVLSYS_MINXP") == null) return 0;
        if (System.getenv("LVLSYS_MAXXP") == null) return 0;

        int minXp = Integer.parseInt(System.getenv("LVLSYS_MINXP"));
        int maxXp = Integer.parseInt(System.getenv("LVLSYS_MAXXP"));

        Random random = new Random();

        return minXp + random.nextInt(maxXp - minXp);
    }

    public void grandLevel(Member member, Long curChan) {

        String baseXpEnv = System.getenv("LVLSYS_BASEXP");
        String maxLvlEnv = System.getenv("LVLSYS_MAXLVL");
        if (baseXpEnv == null || maxLvlEnv == null) {
            return;
        }

        Record3<Integer, Timestamp, Integer> dbUser = getUser(member.getUser());
        int addXp = randomeXP();
        Timestamp curTime = dBase.fetchValue(select(currentTimestamp()));

        if (dbUser == null) {
            setData(member.getUser().getIdLong(), member.getNickname(), member.getUser().getName(), member.getUser().getEffectiveAvatarUrl(), addXp, curTime,0);
            return;
        }

        int level = dbUser.value3();
        int maxLevel = Integer.parseInt(maxLvlEnv);
        if (level >= maxLevel){
            return;
        }

        int xp = dbUser.value1() + addXp;

        int baseXp = Integer.parseInt(baseXpEnv);

        int xpToNextLevel = 5 * (int) Math.pow(level, 2) + (50 * level) + baseXp - xp;

        if (xpToNextLevel <= 0 ){
            level++;
            xp = -xpToNextLevel;
            this.jda.getTextChannelById(curChan).sendMessageEmbeds(new NextLvlEmbed(member.getUser(), level)).complete();
        }

        setData(member.getUser().getIdLong(), member.getNickname(), member.getUser().getName(), member.getUser().getEffectiveAvatarUrl(), xp, curTime, level);

    }
}
