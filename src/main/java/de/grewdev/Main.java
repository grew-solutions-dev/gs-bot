package de.grewdev;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Main {
    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault(System.getenv("BOT_TOKEN"));

        builder.enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                .enableIntents(GatewayIntent.DIRECT_MESSAGES)
                .enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS);

        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        //set default BOT Values
        builder.setStatus(OnlineStatus.valueOf(System.getenv("BOT_STATUS")))
                .setActivity(Activity.customStatus(System.getenv("BOT_ACTIVITY")));

        //add ListenerEvents
        //builder.addEventListeners(new readyEvents());

        try {
            JDA jda = builder.build().awaitReady();
        } catch (InterruptedException e) {
            System.err.println("Bot could not connect!");
            throw new RuntimeException(e);
        }

    }
}
