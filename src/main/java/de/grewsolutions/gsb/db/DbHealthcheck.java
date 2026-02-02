package de.grewsolutions.gsb.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

public final class DbHealthcheck {

    private static final Logger logger = LoggerFactory.getLogger(DbHealthcheck.class);

    private DbHealthcheck() {}

    public static void check() {
        try (Connection con = Database.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT 1")) {

            ps.execute();
            logger.info("DB connection OK");

        } catch (Exception e) {
            throw new IllegalStateException("DB connection failed", e);
        }
    }
}
