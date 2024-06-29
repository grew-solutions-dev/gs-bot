package de.grewdev;

import de.grewdev.events.MemberEventListener;
import de.grewdev.events.ReadyEventListener;
import de.grewdev.events.MessageEventListener;
import de.grewdev.utils.TimeStamper;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault(System.getenv("BOT_TOKEN"));

        builder.setEnableShutdownHook(false);

        builder.enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                .enableIntents(GatewayIntent.DIRECT_MESSAGES)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS);

        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        //set default BOT Values
        builder.setStatus(OnlineStatus.valueOf(System.getenv("BOT_STATUS")))
                .setActivity(Activity.customStatus(System.getenv("BOT_ACTIVITY")));

        //add ListenerEvents
        builder.addEventListeners(new ReadyEventListener());
        builder.addEventListeners(new MemberEventListener());
        builder.addEventListeners(new MessageEventListener());

        JDA jda;
        try {
            jda = builder.build().awaitReady();
        } catch (InterruptedException e) {
            logger.error("Bot could not connect!");
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    logger.info(TimeStamper.getTimestamp() + "Shutting down... sending last message...");
                    TextChannel channel = jda.getTextChannelById(System.getenv("CHAN_BOT_INFO"));
                    if (channel != null) {
                        try {
                            channel.sendMessage(TimeStamper.getTimestamp() +  ":no_entry: Bot is offline ").submit().get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    jda.shutdown();
                })
        );
    }
}
