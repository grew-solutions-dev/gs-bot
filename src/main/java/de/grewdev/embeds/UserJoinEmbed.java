package de.grewdev.embeds;

import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.Collections;

public class UserJoinEmbed extends MessageEmbed {

    public UserJoinEmbed(User user, Guild server) {
        super(null,
                "Welcome " + user.getName() + " to " + server.getName(),
                user.getAsMention() + " you are user no.: " + server.getMemberCount(),
                EmbedType.RICH,
                null,
                Color.GREEN.getRGB(),
                new Thumbnail(user.getAvatarUrl() != null ? user.getAvatarUrl() : server.getIconUrl(), null, 0, 0),
                null,
                new AuthorInfo(null, null, null, null),
                null,
                null,
                null,
                Collections.singletonList(new Field("", "Please read our rules <#" + System.getenv("CHAN_RULES") + "> and verify yourself." +
                        "\nBecause only then can you do other great things.", false)));
    }
}