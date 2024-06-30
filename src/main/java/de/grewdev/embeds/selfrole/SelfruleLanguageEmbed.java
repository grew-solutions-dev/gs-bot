package de.grewdev.embeds.selfrole;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class SelfruleLanguageEmbed extends MessageEmbed{
    public SelfruleLanguageEmbed() {
        super(
                null,
                "Select language",
                "Add your language so that everyone knows what language you speak.\n" +
                        "\uD83C\uDDEC\uD83C\uDDE7 English\n" +
                        "\uD83C\uDDE9\uD83C\uDDEA German",
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
