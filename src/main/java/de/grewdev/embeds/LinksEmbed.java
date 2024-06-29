package de.grewdev.embeds;

import de.grewdev.utils.TimeStamper;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class LinksEmbed extends MessageEmbed {

    public LinksEmbed() {
        super(
                null,
                "Important Links",
                null,
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                null,
                null,
                null,
                null,
                null,
                null,
                Arrays.asList(
                        new Field(System.getenv("LINK_HOMEPAGE_LABLE"), System.getenv("LINK_HOMEPAGE_URL"), false),
                        new Field(System.getenv("LINK_STORE_LABLE"), System.getenv("LINK_STORE_URL"), false),
                        new Field(System.getenv("LINK_DISCORD_LABLE"), System.getenv("LINK_DISCORD_URL"), false),
                        new Field(System.getenv("LINK_DONATION_LABLE"), System.getenv("LINK_DONATION_URL"), false)
                )
        );
    }
}
