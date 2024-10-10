package de.grewdev.embeds;

import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class NextLvlEmbed  extends MessageEmbed {
    public NextLvlEmbed(User user, Integer lvl) {
        super(null,
                "Next Level! " + lvl,
                "Congratulations," +  user.getAsMention() + "!\n You've reached the next level!\n Keep up the great work!",
                EmbedType.RICH,
                null,
                Color.ORANGE.getRGB(),
                new Thumbnail("https://www.iconarchive.com/download/i137452/microsoft/fluentui-emoji-3d/Party-Popper-3d.1024.png", null, 0, 0),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
