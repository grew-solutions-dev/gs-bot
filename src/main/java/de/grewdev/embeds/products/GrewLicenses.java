package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewLicenses extends MessageEmbed {

    public GrewLicenses() {
        super(
                null,
                "Grew Licenses",
                "Grew Licenses is a tool that works together with other scripts.\n" +
                        "In this script licenses are created, edited and read out!\n\n" +
                        "If you have any questions, feel free to open an Ticket :)",
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
                                ":arrow_forward: [es_extendet](https://github.com/esx-framework/esx_core)\n" +
                                        ":arrow_forward: [oxmysql](https://github.com/overextended/oxmysql)\n",
                                false),
                        new Field("Price", "2,50€ (+VAT)", true),
                        new Field("Current Version", "v1.1.1", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_licenses/1-readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/comming-soon", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
