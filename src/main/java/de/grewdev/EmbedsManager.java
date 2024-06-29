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
import org.jooq.meta.derby.sys.Sys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class EmbedsManager {

    private static final Logger logger = LoggerFactory.getLogger(EmbedsManager.class);

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

    private Record2<String, String> getMessageIdIfExists(String embedName, String tableName) {
        return dBase.select(field("embedName", String.class), field("msgId", String.class))
                .from(table(tableName))
                .where(field("embedName", String.class).eq(embedName))
                .fetchOne();
    }

    private void setMessageId(String embedName, String tableName, String msgId, String channelId) {
        dBase.insertInto(table(tableName))
                .columns(field("embedName"), field("msgId"), field("channelId"))
                .values(embedName, msgId, channelId)
                .onDuplicateKeyUpdate()
                .set(field("msgId"),msgId)
                .set(field("channelId"),channelId)
                .execute();
    }

    private void createOrUpdateEmbed(String embedName, String tableName, String channelId, MessageEmbed embed) {
        Record2<String, String> record = getMessageIdIfExists(embedName, tableName);

        TextChannel chan = jda.getTextChannelById(channelId);

        if (chan == null) {
            logger.warn("Could not find channel <#{}> for embed <{}>", channelId, embedName);
            return;
        }

        try {
            if (record != null) {
                String msgId = (String) record.getValue("msgId");
                Message msg = chan.retrieveMessageById(msgId).submit().exceptionally((ex) -> null).get();
                if (msg != null) {
                    msg.editMessageEmbeds(embed).queue();
                }
            }
            Message msg = chan.sendMessageEmbeds(embed).complete();
            setMessageId(embedName, tableName, msg.getId(), chan.getId());
        } catch (Exception e) {
            logger.warn("Error while creating or updating embed <{}> in channel <#{}>",embedName,channelId,e);
        }
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

    public void createOrUpdateEmbeds() {
        createOrUpdateEmbed("ruleEmbed","fixedEmbeds", System.getenv("CHAN_RULES"),new RuleEmbed());
    }
}
