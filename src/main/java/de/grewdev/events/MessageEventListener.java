package de.grewdev.events;

import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class MessageEventListener extends ListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getId().equals(System.getenv("CHAN_RULES")) && !event.getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            event.getMessage().delete().queue();
            if (event.getMessage().getContentRaw().equalsIgnoreCase(System.getenv("CMD_ACCEPT_RULES"))) {
                try {
                    event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getAuthor().getId()), Objects.requireNonNull(event.getGuild().getRoleById(System.getenv("ROLE_USER")))).complete();
                } catch (Exception e) {
                    logger.error("Could not assign role <{}> to user <{}> with userId <{}>", System.getenv("ROLE_USER"), event.getAuthor().getGlobalName(), event.getAuthor().getId());
                }
            }
        }
    }
}
