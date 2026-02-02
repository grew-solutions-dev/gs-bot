package de.grewsolutions.gsb;

import de.grewsolutions.gsb.db.Database;
import de.grewsolutions.gsb.db.DbHealthcheck;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        // load Token
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("BOT_TOKEN");

        if (token == null || token.isBlank()) {
            throw new IllegalStateException("BOT_TOKEN is missing in .env");
        }

        // load DBase
        String dbUrl = dotenv.get("DB_URL");
        String dbUser = dotenv.get("DB_USER");
        String dbPass = dotenv.get("DB_PASS");

        Database.init(dbUrl, dbUser, dbPass);
        DbHealthcheck.check();
        Runtime.getRuntime().addShutdownHook(new Thread(Database::shutdown));

        // create JDA
        JDA jda = JDABuilder.createDefault(token).build();

        jda.awaitReady();
        logger.info("[GS] Bot is READY!");
    }
}
