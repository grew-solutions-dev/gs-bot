package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewNPC extends MessageEmbed {

    public GrewNPC() {
        super(
                null,
                "Grew NPC",
                """
                        Grew NPC adds individual NPC's with 3D text.
                        NPC's can be taken to use information.
                        In the future, new features will come.

                        If you have any questions, feel free to open an Ticket :)""",
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                new Thumbnail("https://dunb17ur4ymx4.cloudfront.net/packages/images/b70145489767495d11e09bf91d050524c063c4fe.png", null, 0, 0),
                null,
                null,
                null,
                null,
                null,
                Arrays.asList(
                        new Field("Requirements",
                                ":name_badge: No Requirements",
                                false),
                        new Field("Price", "2,75€ (+VAT)", true),
                        new Field("Current Version", "v1.2.1", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_npc/readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/5453979", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
