package de.grewdev.events;

import de.grewdev.utils.manager.EmbedsManager;
import de.grewdev.embeds.selfrole.SelfruleDevEmbed;
import de.grewdev.embeds.selfrole.SelfruleLanguageEmbed;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.entities.channel.ChannelType;
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
    private static final HashMap<String, String> allEmbedIds = new HashMap<>();

    @Override
    public void onReady(ReadyEvent event) {
        EmbedsManager embedsManager = EmbedsManager.getInstance(event.getJDA());

        // Create Selfrules Embed
        Message languageEmbedMsg = embedsManager.createOrUpdateEmbed("languageEmbed", "selfRuleEmbeds", System.getenv("CHAN_SELFROLES"), new SelfruleLanguageEmbed());
        if (languageEmbedMsg != null) {
            for (String s : Arrays.asList("\uD83C\uDDEC\uD83C\uDDE7", "\uD83C\uDDE9\uD83C\uDDEA")) {
                languageEmbedMsg.addReaction(Emoji.fromFormatted(s)).queue();
                allEmbedIds.put("languageEmbed", languageEmbedMsg.getId());
            }
        }

        Message progEmbedMsg = embedsManager.createOrUpdateEmbed("devEmbed", "selfRuleEmbeds", System.getenv("CHAN_SELFROLES"), new SelfruleDevEmbed());
        if (progEmbedMsg != null) {
            for (String s : Arrays.asList("<:php512:1256945801545125961>", "<:lua512:1256945793043005450>", "<:js512:1256945797577179247>", "<:java256:1256945795878354947>", "<:c512:1256945791327535115>", "<:nodejs512:1256945800425115770>", "<:mysql256:1256945794465140806>")) {
                progEmbedMsg.addReaction(Emoji.fromFormatted(s)).queue();
                allEmbedIds.put("devEmbed", progEmbedMsg.getId());
            }
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.isFromType(ChannelType.PRIVATE)) {
            return;
        }

        TextChannel reactChan = event.getChannel().asTextChannel();
        String reactMsgId = event.getMessageId();

        if (reactChan.getId().equals(System.getenv("CHAN_SELFROLES"))) {
            if (allEmbedIds.containsValue(reactMsgId)) {
                switch (event.getEmoji().getFormatted()) {
                    case "\uD83C\uDDE9\uD83C\uDDEA": // German
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_GER"))).queue();
                        break;

                    case "\uD83C\uDDEC\uD83C\uDDE7": // English
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_GB"))).queue();
                        break;

                    default:

                }

                switch (event.getEmoji().getFormatted()) {
                    case "<:php512:1256945801545125961>": // PHP
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_PHP"))).queue();
                        break;

                    case "<:lua512:1256945793043005450>": // LUA
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_LUA"))).queue();
                        break;

                    case "<:js512:1256945797577179247>": // JavaScript
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_JS"))).queue();
                        break;

                    case "<:java256:1256945795878354947>": // Java
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_JAVA"))).queue();
                        break;

                    case "<:c512:1256945791327535115>": // C
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_CSHARP"))).queue();
                        break;

                    case "<:nodejs512:1256945800425115770>": // Node.js
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_NODEJS"))).queue();
                        break;

                    case "<:mysql256:1256945794465140806>": // MySQL
                        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_MYSQL"))).queue();
                        break;

                    default:
                }
            }
        }
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        if (event.isFromType(ChannelType.PRIVATE)) {
            return;
        }

        TextChannel reactChan = event.getChannel().asTextChannel();
        String reactMsgId = event.getMessageId();

        if (reactChan.getId().equals(System.getenv("CHAN_SELFROLES"))) {
            if (allEmbedIds.containsValue(reactMsgId)) {
                switch (event.getEmoji().getFormatted()) {
                    case "\uD83C\uDDE9\uD83C\uDDEA": // German
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_GER"))).queue();
                        break;

                    case "\uD83C\uDDEC\uD83C\uDDE7": // English
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_GB"))).queue();
                        break;

                    default:
                }

                switch (event.getEmoji().getFormatted()) {
                    case "<:php512:1256945801545125961>": // PHP
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_PHP"))).queue();
                        break;

                    case "<:lua512:1256945793043005450>": // LUA
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_LUA"))).queue();
                        break;

                    case "<:js512:1256945797577179247>": // JavaScript
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_JS"))).queue();
                        break;

                    case "<:java256:1256945795878354947>": // Java
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_JAVA"))).queue();
                        break;

                    case "<:c512:1256945791327535115>": // C
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_CSHARP"))).queue();
                        break;

                    case "<:nodejs512:1256945800425115770>": // Node.js
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_NODEJS"))).queue();
                        break;

                    case "<:mysql256:1256945794465140806>": // MySQL
                        event.getGuild().removeRoleFromMember(UserSnowflake.fromId(event.getMember().getId()), event.getGuild().getRoleById(System.getenv("ROLE_MYSQL"))).queue();
                        break;

                    default:
                }
            }
        }
    }
}
