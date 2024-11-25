package de.grewdev.utils.manager;

import de.grewdev.utils.DatabaseConnection;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.TIMESTAMP;

public class BroadcastMessageManager {

    private static BroadcastMessageManager instance;
    private final DSLContext dBase;
    private final Map<String, String> broadcastMessages = new HashMap<>();

    private BroadcastMessageManager() {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        this.dBase = DSL.using(connection, SQLDialect.MYSQL);

        createIfNotExistsEmbedTable("broadcastMessages");
        cacheBroadcastMessages();
    }

    public static synchronized BroadcastMessageManager getInstance() {
        if (instance == null) {
            instance = new BroadcastMessageManager();
        }
        return instance;
    }

    private void createIfNotExistsEmbedTable(String tableName) {
        dBase.createTableIfNotExists(tableName)
                .column("originalMessageId", BIGINT.notNull())
                .column("broadcastMessageId", BIGINT.notNull())
                .column("msgTimestamp", TIMESTAMP.notNull().defaultValue(DSL.currentTimestamp()))
                .primaryKey("originalMessageId")
                .execute();
    }

    private void cacheBroadcastMessages() {
        Result<Record2<Long, Long>> result = dBase.select(
                        field("originalMessageId", BIGINT),
                        field("broadcastMessageId", BIGINT)
                )
                .from(table("broadcastMessages"))
                .fetch();

        // Cache die Broadcast-Nachrichten
        for (Record2<Long, Long> record : result) {
            broadcastMessages.put(record.getValue("originalMessageId").toString(), record.getValue("broadcastMessageId").toString());
        }
    }

    public void addBroadcastMessage(String originalMessageId, String broadcastMessageId) {
        broadcastMessages.put(originalMessageId, broadcastMessageId);
        dBase.insertInto(table("broadcastMessages"))
                .columns(field("originalMessageId"), field("broadcastMessageId"))
                .values(Long.parseLong(originalMessageId), Long.parseLong(broadcastMessageId))
                .execute();
    }

    public void removeBroadcastMessage(String originalMessageId) {
        broadcastMessages.remove(originalMessageId);
        dBase.deleteFrom(table("broadcastMessages"))
                .where(field("originalMessageId").eq(Long.parseLong(originalMessageId)))
                .execute();
    }

    public String getBroadcastMessageId(String originalMessageId) {
        return broadcastMessages.get(originalMessageId);
    }

    public Map<String, String> getBroadcastMessages() {
        return broadcastMessages;
    }
}
