package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewBillingsystem extends MessageEmbed {

    public GrewBillingsystem() {
        super(
                null,
                "Grew Billingsystem",
                """
                        This script allows you to create, pay and display invoices.
                        You can also use this script to write certain job invoices so that the money goes into a specific job fund.

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
                        new Field("Price", "5,00€ (+VAT)", true),
                        new Field("Current Version", "v1.3.1", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_billingsystem/1-readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/comming-soon", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
