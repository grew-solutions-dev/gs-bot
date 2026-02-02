package de.grewsolutions.gsb.db;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    private static HikariDataSource dataSource;

    private Database() {}

    public static void init(String jdbcUrl, String user, String pass) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(user);
        config.setPassword(pass);

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setPoolName("gsb-pool");

        dataSource = new HikariDataSource(config);
        logger.info("HikariCP initialized");
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            throw new IllegalStateException("Database not initialized");
        }
        return dataSource;
    }

    public static void shutdown() {
        if (dataSource != null) {
            dataSource.close();
            logger.info("HikariCP shutdown");
        }
    }
}
