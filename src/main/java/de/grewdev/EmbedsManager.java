package de.grewdev;

import de.grewdev.embeds.RuleEmbed;
import de.grewdev.utils.DatabaseConnection;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.SQLDialect;
import java.sql.Connection;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class EmbedsManager {

    private final DSLContext dBase;
    private final JDA jda;

    private void createIfNotExistsEmbedTable(String tableName) {
        dBase.createTableIfNotExists(tableName)
                .column("embedName", VARCHAR(36))
                .column("msgId", VARCHAR(25))
                .column("channelId", VARCHAR(25))
                .primaryKey("embedName")
                .execute();
    }

    public EmbedsManager(JDA jda) {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        this.dBase = using(connection, SQLDialect.MYSQL);
        this.jda = jda;

        //create EmbedsTables
        createIfNotExistsEmbedTable("fixedEmbeds");
        createIfNotExistsEmbedTable("productEmbeds");
        createIfNotExistsEmbedTable("otherEmbeds");
    }

}
