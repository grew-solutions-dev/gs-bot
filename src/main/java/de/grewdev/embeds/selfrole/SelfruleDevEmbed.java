package de.grewdev.embeds.selfrole;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class SelfruleDevEmbed extends MessageEmbed {
    public SelfruleDevEmbed() {
        super(
                null,
                "Select programming languages",
                "Add the language you are programming with.\n" +
                        "<:php512:1256945801545125961> PHP\n" +
                        "<:lua512:1256945793043005450> LUA\n" +
                        "<:js512:1256945797577179247> JavaScript\n" +
                        "<:java256:1256945795878354947> Java\n" +
                        "<:c512:1256945791327535115> C#\n" +
                        "<:nodejs512:1256945800425115770> NodeJs\n" +
                        "<:mysql256:1256945794465140806> MySQL",
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
