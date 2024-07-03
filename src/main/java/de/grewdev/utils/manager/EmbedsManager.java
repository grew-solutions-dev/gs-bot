package de.grewdev.utils.manager;

import de.grewdev.utils.DatabaseConnection;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class EmbedsManager {

    private static final Logger logger = LoggerFactory.getLogger(EmbedsManager.class);
    private static EmbedsManager instance;
    private final JDA jda;
    private final DSLContext dBase;

    private EmbedsManager(JDA jda) {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        this.dBase = DSL.using(connection, SQLDialect.MYSQL);
        this.jda = jda;

        //create EmbedsTables
        createIfNotExistsEmbedTable("fixedEmbeds");
        createIfNotExistsEmbedTable("productEmbeds");
        createIfNotExistsEmbedTable("selfRuleEmbeds");
        createIfNotExistsEmbedTable("otherEmbeds");
    }

    public static EmbedsManager getInstance(JDA jda) {
        if (instance == null) {
            instance = new EmbedsManager(jda);
        }
        return instance;
    }

    private void createIfNotExistsEmbedTable(String tableName) {
        dBase.createTableIfNotExists(tableName)
                .column("embedName", VARCHAR(36))
                .column("msgId", VARCHAR(25))
                .column("channelId", VARCHAR(25))
                .primaryKey("embedName")
                .execute();
    }

    private void setMessageId(String tableName, String embedName, String msgId, String channelId) {
        dBase.insertInto(table(tableName))
                .columns(field("embedName"), field("msgId"), field("channelId"))
                .values(embedName, msgId, channelId)
                .onDuplicateKeyUpdate()
                .set(field("msgId"), msgId)
                .set(field("channelId"), channelId)
                .execute();

    }

    private String getMessageIdIfExist(String embedName, String tableName) {
        Record1<String> result = dBase.select(field("msgId", String.class))
                .from(table(tableName))
                .where(field("embedName", String.class).eq(embedName))
                .fetchOne();

        if (result != null) {
            return result.getValue("msgId").toString();
        }
        return null;
    }

    public Message createOrUpdateEmbed(String embedName, String tableName, String channelId, MessageEmbed embed) {
        String msgId = getMessageIdIfExist(embedName, tableName);
        TextChannel chan = jda.getTextChannelById(channelId);

        if (chan == null) {
            logger.warn("Could not find channel <#{}> for embed <{}>", channelId, embedName);
            return null;
        }

        try {
            if (msgId != null) {
                Message msg = chan.retrieveMessageById(msgId).submit().exceptionally(ex -> null).get();

                if (msg != null) {
                    msg.editMessageEmbeds(embed).complete();
                    return msg;
                }
            }
        } catch (Exception e) {
            logger.warn("Error while creating or updating embed <{}> in channel <#{}>", embedName, channelId, e);
            return null;
        }

        Message msg = chan.sendMessageEmbeds(embed).complete();
        setMessageId(tableName, embedName, msg.getId(), chan.getId());
        return msg;
    }
}
