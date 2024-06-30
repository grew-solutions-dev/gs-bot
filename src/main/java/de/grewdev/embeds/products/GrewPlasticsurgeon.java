package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewPlasticsurgeon extends MessageEmbed {

    public GrewPlasticsurgeon() {
        super(
                null,
                "Grew PlasticSurgeon",
                "Grew plastic surgeon adds an NPC who can stand at the entry or at the hospital to approach a skin change for with/without money.\n" +
                        "A must for roleplay servers!\n\n" +
                        "If you have any questions, feel free to open an Ticket :)",
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                new Thumbnail("https://dunb17ur4ymx4.cloudfront.net/packages/images/aaf9d5f8a7069a9863e2c3376e013d34f2d6c774.png", null, 0, 0),
                null,
                null,
                null,
                null,
                null,
                Arrays.asList(
                        new Field("Requirements",
                                ":arrow_forward: [es_extendet](https://github.com/esx-framework/esx_core)\n" +
                                        ":arrow_forward: [esx_skin](https://github.com/esx-framework/esx_core)\n",
                                false),
                        new Field("Price", "4,50€ (+VAT)", true),
                        new Field("Current Version", "v2.1.2", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_plasticsurgeon/1-readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/5571869", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
