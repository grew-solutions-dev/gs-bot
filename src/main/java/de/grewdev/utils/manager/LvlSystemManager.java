package de.grewdev.utils.manager;

import de.grewdev.utils.DatabaseConnection;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Timestamp;

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

    public boolean hasMsgMinLength(Message msg) {
        if (System.getenv("LVLSYS_MINCHARS") == null) return false;
        return msg.getContentRaw().length() >= Integer.parseInt(System.getenv("LVLSYS_MINCHARS"));

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
}
