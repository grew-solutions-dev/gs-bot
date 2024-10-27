package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewGaragesystem extends MessageEmbed {

    public GrewGaragesystem() {
        super(
                null,
                "Grew Garagesystem",
                """
                        Grew Garagesystem is a full Garagesystem with tow yard.
                        Easily adjustable garages for users.
                        NativeUI menu at each garage, specifically for certain vehicles.
                        Pre-set garage without database access.
                        Separate commands for team members to put vehicles in the garage, command to save the vehicle data.
                        Tow yard included, with adjustable price and multiple locations.
                        Extensive config file with explanation.

                        If you have any questions, feel free to open an Ticket :)""",
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                new Thumbnail("https://dunb17ur4ymx4.cloudfront.net/packages/images/dfa6b1b2e5901bc39931250e8e6d25faf83782d4.png", null, 0, 0),
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
                                        :arrow_forward: [NativeUILua](https://github.com/grew-development/NativeUILua)
                                        :arrow_forward: [zf_dialog](https://github.com/zf-labo/zf_dialog)
                                        :arrow_forward: [swt_notification](https://github.com/Switty6/swt_notifications)
                                        """,
                                false),
                        new Field("Price", "20,00€ (+VAT)", true),
                        new Field("Current Version", "v1.3.1", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_garagesystem/readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/5486208", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
