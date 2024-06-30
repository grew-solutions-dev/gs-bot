package de.grewdev;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import de.grewdev.embeds.ErrorEmbed;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class BotErrorLogger extends AppenderBase<ILoggingEvent> {

    private JDA jda;
    private String channelId;

    public void setJda(JDA jda) {
        this.jda = jda;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {

        //Only log errors
        if (jda != null && eventObject.getLevel().equals(Level.ERROR)) {
            TextChannel channel = jda.getTextChannelById(channelId);
            if (channel != null) {
                channel.sendMessageEmbeds(new ErrorEmbed(eventObject)).queue();
            }
        }
    }
}
