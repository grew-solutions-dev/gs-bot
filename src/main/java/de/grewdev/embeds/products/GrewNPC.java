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
                        This script removes default spawning NPCs (such as civilians, vehicles, and animals) from the game world and
                        allows you to place custom NPCs at specific locations with text displayed above their heads.
                        The script offers high flexibility through configuration settings and JSON files for defining NPCs.
                        """,
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                new Thumbnail(System.getenv("LINK_PRODUCT_THUMBNAIL"), null, 0, 0),
                null,
                null,
                null,
                null,
                null,
                Arrays.asList(
                        new Field("Requirements",
                                System.getenv("LINK_WIKI_URL") + "/grew_npc/required",
                                false),
                        new Field("Price", "2,75€ (+VAT)", true),
                        new Field("Current Version", "v2.1.3", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_npc/readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/5453979", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
