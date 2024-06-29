package de.grewdev.embeds;

import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.Collections;

public class UserLeftEmbed extends MessageEmbed {

    public UserLeftEmbed(User user, Guild server) {
        super(null,
                "Goodbye " + user.getName() + " from " + server.getName(),
                user.getAsMention() + ", you were user no.: " + (server.getMemberCount() + 1),
                EmbedType.RICH,
                null,
                Color.RED.getRGB(),
                new Thumbnail(user.getAvatarUrl() != null ? user.getAvatarUrl() : server.getIconUrl(), null, 0, 0),
                null,
                new AuthorInfo(null, null, null, null),
                null,
                null,
                null,
                Collections.singletonList(new Field("New member count: " + server.getMemberCount(), "", false)));
    }
}