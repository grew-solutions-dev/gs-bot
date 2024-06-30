package de.grewdev.events;

import de.grewdev.EmbedsManager;
import de.grewdev.embeds.selfrole.SelfruleLanguageEmbed;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;

public class MessageInteractionEventListener extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MessageInteractionEventListener.class);
    private static HashMap<String, String> allEmbedIds = new HashMap<>();

    @Override
    public void onReady(ReadyEvent event) {
        EmbedsManager embedsManager = EmbedsManager.getInstance(event.getJDA());

        // Create Selfrules Embed
        Message languageEmbedMsg = embedsManager.createOrUpdateEmbed("languageEmbed","selfRuleEmbeds", System.getenv("CHAN_SELFROLES"),new SelfruleLanguageEmbed());
        if (languageEmbedMsg != null) {
            for (String s : Arrays.asList("\uD83C\uDDEC\uD83C\uDDE7", "\uD83C\uDDE9\uD83C\uDDEA")) {
                languageEmbedMsg.addReaction(Emoji.fromFormatted(s)).queue();
                allEmbedIds.put("languageEmbed", languageEmbedMsg.getId());
            }
        }

    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        TextChannel reactChan = event.getChannel().asTextChannel();
        String reactMsgId = event.getMessageId();

        if (reactChan.getId().equals(System.getenv("CHAN_SELFROLES"))){
            if(allEmbedIds.containsValue(reactMsgId)) {
                switch (event.getEmoji().getFormatted()) {
                    case "\uD83C\uDDE9\uD83C\uDDEA": // German
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_GER"))).queue();
                        break;

                    case "\uD83C\uDDEC\uD83C\uDDE7": // English
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_GB"))).queue();
                        break;

                    default:

                }
            }
        }
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        TextChannel reactChan = event.getChannel().asTextChannel();
        String reactMsgId = event.getMessageId();

        if (reactChan.getId().equals(System.getenv("CHAN_SELFROLES"))){
            if(allEmbedIds.containsValue(reactMsgId)) {
                switch (event.getEmoji().getFormatted()) {
                    case "\uD83C\uDDE9\uD83C\uDDEA": // German
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_GER"))).queue();
                        break;

                    case "\uD83C\uDDEC\uD83C\uDDE7": // English
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_GB"))).queue();
                        break;

                    default:

                }
            }
        }
    }
}
