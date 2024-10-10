package de.grewdev.utils.manager;

import de.grewdev.utils.DatabaseConnection;
import net.dv8tion.jda.api.JDA;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import net.dv8tion.jda.api.entities.Message;
import org.jooq.impl.DSL;
import org.jooq.impl.QOM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

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

}
