package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewInventory extends MessageEmbed {

    public GrewInventory() {
        super(
                null,
                "Grew Inventory",
                """
                        Grew Inventory creates your own individual inventory.
                        It no longer allows your users to use the standardized F2 menu but gives you new functions to interact with all your items!

                        If you have any questions, feel free to open an Ticket :)""",
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                new Thumbnail("https://cdn-icons-png.freepik.com/512/6581/6581218.png", null, 0, 0),
                null,
                null,
                null,
                null,
                null,
                Arrays.asList(
                        new Field("Requirements",
                                """
                                        :arrow_forward: [es_extendet](https://github.com/esx-framework/esx_core)
                                        :arrow_forward: [oxmysql](https://github.com/overextended/oxmysql)
                                        """,
                                false),
                        new Field("Price", "10,00€ (+VAT)", true),
                        new Field("Current Version", "v0.1.1", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_inventory/readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/comming-soon", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
