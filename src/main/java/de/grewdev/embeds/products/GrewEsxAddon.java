package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewEsxAddon extends MessageEmbed {

    public GrewEsxAddon() {
        super(
                null,
                "Grew esxAddon",
                """
                        Grew esxAddon adds new commands for es_extendet.
                        Additional events are also brought by the script. For more information please read the wiki

                        If you have any questions, feel free to open an Ticket :)""",
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
                                System.getenv("LINK_WIKI_URL") + "/grew_esxaddon/required",
                                false),
                        new Field("Price", "3,00€ (+VAT)", true),
                        new Field("Current Version", "v1.6.5", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_esxaddon/readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/5454068", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
