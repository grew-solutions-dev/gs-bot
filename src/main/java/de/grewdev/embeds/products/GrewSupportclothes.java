package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewSupportclothes extends MessageEmbed {

    public GrewSupportclothes() {
        super(
                null,
                "Grew Supportclothes",
                """
                        Grew Supportclothes is a perfomant Admin-Vest-System with special Ranks.\
                        You have individual ranks for each vest, which can be added to other scripts.
                        For example, the Door Creator.
                        You have the possibility to simply add more vests and give them a rank.

                        If you have any questions, feel free to open an Ticket :)""",
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                new Thumbnail("https://dunb17ur4ymx4.cloudfront.net/packages/images/a506576005ef61641fbc0c061da2537a823e63bd.png", null, 0, 0),
                null,
                null,
                null,
                null,
                null,
                Arrays.asList(
                        new Field("Requirements",
                                """
                                        :arrow_forward: [es_extendet](https://github.com/esx-framework/esx_core)
                                        :arrow_forward: [esx_skin](https://github.com/esx-framework/esx_core)
                                        """,
                                false),
                        new Field("Price", "15,00€ (+VAT)", true),
                        new Field("Current Version", "v1.3.2", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_supportclothes/1-readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/5452767", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
