package de.grewdev.events;

import de.grewdev.utils.TimeStamper;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.session.ReadyEvent;

public class ReadyEventListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        TextChannel tc = event.getJDA().getTextChannelById(System.getenv("CHAN_BOT_INFO"));
        if (tc != null) {
            tc.sendMessage(TimeStamper.getTimestamp() + ":white_check_mark: Bot is running now!")
                    .queue();
        }
    }
}
