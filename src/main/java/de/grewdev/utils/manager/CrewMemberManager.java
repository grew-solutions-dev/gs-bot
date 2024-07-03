package de.grewdev.utils.manager;

import de.grewdev.utils.DatabaseConnection;
import net.dv8tion.jda.api.entities.Member;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class CrewMemberManager {

    private static final Logger logger = LoggerFactory.getLogger(CrewMemberManager.class);
    private static CrewMemberManager instance;
    private final DSLContext dBase;

    private List<String> allowCrewList;

    private CrewMemberManager() {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        this.dBase = DSL.using(connection, SQLDialect.MYSQL);

        //create Table
        createIfNotExistsEmbedTable("crewMembers");
        cachMemberIds();
    }

    private void createIfNotExistsEmbedTable(String tableName) {
        dBase.createTableIfNotExists(tableName)
                .column("memberName", VARCHAR(50))
                .column("memberId", VARCHAR(36))
                .primaryKey("memberId")
                .execute();
    }

    private void cachMemberIds() {
        Result<Record1<String>> result = dBase.select(
                    field("memberId", String.class)
                )
                .from(table("crewMembers"))
                .fetch();

        allowCrewList = result.getValues(field("memberId", String.class));
    }

    public List<String> getMemberIds() {
        return allowCrewList;
    }

    public Boolean isCrewMember(String memberId) {
        return allowCrewList.contains(memberId);
    }

    public Boolean addMember(Member member) {
        Integer error =  dBase.insertInto(table("crewMembers"))
                .columns(field("memberName", String.class), field("memberId", String.class))
                .values(member.getEffectiveName(), member.getId())
                .execute();

        cachMemberIds();
        return error.equals(1);
    }

    public Boolean removeMember(String memberId) {
        Integer error =  dBase.deleteFrom(table("crewMembers"))
                .where(field("memberId", String.class).eq(memberId))
                .execute();

        cachMemberIds();
        return error.equals(1);
    }

    public static CrewMemberManager getInstance() {
        if (instance == null) {
            instance = new CrewMemberManager();
        }
        return instance;
    }
}
