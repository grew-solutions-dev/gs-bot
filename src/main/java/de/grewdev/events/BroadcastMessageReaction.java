package de.grewdev.events;

import de.grewdev.utils.manager.CrewMemberManager;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.NewsChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BroadcastMessageReaction extends ListenerAdapter {

    private List<String> allowCrewList;

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        allowCrewList = CrewMemberManager.getInstance().getMemberIds();
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {

        if (event.isFromType(ChannelType.PRIVATE)) {
            if (allowCrewList == null) {
                return;
            }

            switch (event.getEmoji().getFormatted()) {
                case "\uD83D\uDCE2": // Loudspeaker
                    for (String id : allowCrewList) {
                        if (event.getUser().getId().equals(id.trim())) {
                            NewsChannel NewsChan = event.getJDA().getGuildById(System.getenv("SERVER_ID")).getNewsChannelById(System.getenv("CHAN_ANNOUNCE"));
                            //TextChannel NewsChan = event.getJDA().getTextChannelById(System.getenv("CHAN_BOT_CONFIG"));

                            if (NewsChan == null) {
                                System.out.println("NewsChannel konnte nicht gefunden werden!");
                                return;
                            }

                            NewsChan.sendTyping().queue();
                            try {
                                Thread.sleep(2500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }


                            event.retrieveMessage().queue(msg -> {
                                NewsChan.sendMessage(msg.getContentDisplay()).queue(msg2 -> msg2.crosspost().queue());
                            });
                            break;
                        }
                    }
                    break;
                default:
                    return;
            }
        }
    }
}
