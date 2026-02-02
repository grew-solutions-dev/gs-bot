package de.grewsolutions.gsb;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("BOT_TOKEN");

        if (token == null || token.isBlank()) {
            throw new IllegalStateException("BOT_TOKEN is missing in .env");
        }

        JDA jda = JDABuilder.createDefault(token).build();

        jda.awaitReady();
        logger.info("[GS] Bot is READY!");
    }
}
