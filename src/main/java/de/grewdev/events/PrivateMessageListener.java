package de.grewdev.events;

import de.grewdev.utils.manager.CrewMemberManager;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.NewsChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PrivateMessageListener extends ListenerAdapter {

    private List<String> allowCrewList;

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        allowCrewList = CrewMemberManager.getInstance().getMemberIds();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.isFromType(ChannelType.PRIVATE) && !event.getAuthor().isBot()) {
           if (allowCrewList == null) {
                return;
           }

           for (String id : allowCrewList) {
                if (event.getAuthor().getId().equals(id.trim())) {
                    //TODO Adding symbols to the MSG for the dispatch query
                    NewsChannel NewsChan = event.getJDA().getGuildById(System.getenv("SERVER_ID")).getNewsChannelById(System.getenv("CHAN_ANNOUNCE"));
                    //TextChannel NewsChan = event.getJDA().getTextChannelById(System.getenv("CHAN_BOT_CONFIG"));
                    NewsChan.sendTyping().queue();
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    NewsChan.sendMessage(event.getMessage().getContentDisplay()).queue(msg -> msg.crosspost().queue());
                    break;
                }
           }
        }
    }
}
