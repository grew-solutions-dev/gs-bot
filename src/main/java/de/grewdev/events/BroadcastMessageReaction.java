package de.grewdev.events;

import de.grewdev.utils.manager.BroadcastMessageManager;
import de.grewdev.utils.manager.CrewMemberManager;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.NewsChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BroadcastMessageReaction extends ListenerAdapter {

    private List<String> allowCrewList;
    private final BroadcastMessageManager manager = BroadcastMessageManager.getInstance();

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

            String userId = event.getUserId();
            if (!allowCrewList.contains(userId)) {
                return;
            }

            //TextChannel NewsChan = event.getJDA().getTextChannelById(System.getenv("CHAN_BOT_CONFIG"));
            NewsChannel NewsChan = event.getJDA().getGuildById(System.getenv("SERVER_ID")).getNewsChannelById(System.getenv("CHAN_ANNOUNCE"));

            switch (event.getEmoji().getFormatted()) {
                case "\uD83D\uDCE2": // Loudspeaker
                    String broadcastMessageId = manager.getBroadcastMessageId(event.getMessageId());
                    if (broadcastMessageId != null) {
                        return;
                    }

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
                        NewsChan.sendMessage(msg.getContentDisplay()).queue(msg2 -> {
                            manager.addBroadcastMessage(msg.getId(), msg2.getId());
                            msg2.crosspost().queue();
                        });
                    });

                case "\uD83D\uDDD1\uFE0F": //wastebasket
                    String messageId = event.getMessageId();
                    String broadcastId = manager.getBroadcastMessageId(messageId);

                    if (broadcastId == null) {
                        return;
                    }

                    if (NewsChan == null) {
                        System.out.println("Broadcast-Channel konnte nicht gefunden werden!");
                        return;
                    }

                    NewsChan.retrieveMessageById(broadcastId).queue(
                            broadcastMessage -> {
                                broadcastMessage.delete().queue();
                                manager.removeBroadcastMessage(messageId);
                            },
                            throwable -> {
                                manager.removeBroadcastMessage(messageId);
                            }
                    );
                    break;

                default:
                    return;
            }
        }
    }

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        if (event.isFromType(ChannelType.PRIVATE)) {
            String originalMessageId = event.getMessageId();
            String broadcastMessageId = manager.getBroadcastMessageId(originalMessageId);

            if (broadcastMessageId != null) {

                //TextChannel NewsChan = event.getJDA().getTextChannelById(System.getenv("CHAN_BOT_CONFIG"));
                NewsChannel NewsChan = event.getJDA().getGuildById(System.getenv("SERVER_ID")).getNewsChannelById(System.getenv("CHAN_ANNOUNCE"));

                if (NewsChan == null) {
                    System.out.println("NewsChannel konnte nicht gefunden werden!");
                    return;
                }

                Message oriMsg = event.getMessage();
                NewsChan.retrieveMessageById(broadcastMessageId).queue(
                        broadcastMsg -> {
                            broadcastMsg.editMessage(oriMsg.getContentDisplay()).queue();
                        },
                        throwable -> {
                            manager.removeBroadcastMessage(originalMessageId);
                        }
                );
            }
        }
    }
}
