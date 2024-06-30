package de.grewdev.events;

import de.grewdev.embeds.UserJoinEmbed;
import de.grewdev.embeds.UserLeftEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MemberEventListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        TextChannel lobbychan = event.getJDA().getTextChannelById(System.getenv("CHAN_LOBBY"));
        if (lobbychan != null) {
            lobbychan.sendMessageEmbeds(new UserJoinEmbed(event.getUser(), event.getGuild())).queue();
        }
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        TextChannel lobbychan = event.getJDA().getTextChannelById(System.getenv("CHAN_LOBBY"));
        if (lobbychan != null) {
            lobbychan.sendMessageEmbeds(new UserLeftEmbed(event.getUser(), event.getGuild())).queue();
        }
    }
}
