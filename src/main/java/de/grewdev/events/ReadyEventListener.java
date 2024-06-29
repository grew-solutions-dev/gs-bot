package de.grewdev.events;

import de.grewdev.EmbedsManager;
import de.grewdev.utils.TimeStamper;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadyEventListener extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ReadyEventListener.class);

    @Override
    public void onReady(ReadyEvent event) {
        TextChannel tc = event.getJDA().getTextChannelById(System.getenv("CHAN_BOT_STATUS"));
        if (tc != null) {
            tc.sendMessage(TimeStamper.getTimestamp() + ":white_check_mark: Bot is running now!")
                    .queue();
        }

        EmbedsManager embedsManager = new EmbedsManager(event.getJDA());
        embedsManager.createOrUpdateEmbeds();
    }
}
