package de.grewdev.events;

import de.grewdev.utils.manager.LvlSystemManager;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LvLMessageEventListener  extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        LvlSystemManager lvlmanager = LvlSystemManager.getInstance(event.getJDA());

        if (event.getAuthor().isBot()) return;
        if (event.getMessage().isFromType(ChannelType.PRIVATE)) return;
        if (lvlmanager.isTimeOut(event.getAuthor())) return;

        lvlmanager.grandLevel(event.getMember(), event.getChannel().getIdLong());

    }
}
