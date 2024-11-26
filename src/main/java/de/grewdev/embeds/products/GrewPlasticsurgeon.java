package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewPlasticsurgeon extends MessageEmbed {

    public GrewPlasticsurgeon() {
        super(
                null,
                "Grew PlasticSurgeon",
                """
                        The "Grew Plasticsurgeon" script introduces a plastic surgeon to your game, allowing players to customize their outfits and facial features.
                        All changes are saved permanently, adding a new layer of individuality and roleplay possibilities.
                        With "Grew Plasticsurgeon," you can enhance your gameplay experience and create a more dynamic environment for all players!
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
                                System.getenv("LINK_WIKI_URL") + "/grew_plasticsurgeon/required",
                                false),
                        new Field("Price", "5,00€ (+VAT)", true),
                        new Field("Current Version", "v2.2.0", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_plasticsurgeon/readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/5571869", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
