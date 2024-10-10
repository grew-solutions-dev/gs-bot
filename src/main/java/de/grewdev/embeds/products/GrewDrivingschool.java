package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewDrivingschool extends MessageEmbed {

    public GrewDrivingschool() {
        super(
                null,
                "Grew Drivingschool",
                """
                        DrivingSchool for FiveM with different driving routes, customizable speeds and much more.

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
                                ":arrow_forward: [es_extendet](https://github.com/esx-framework/esx_core)\n" +
                                        ":arrow_forward: [NativeUILua](https://github.com/grew-development/NativeUILua)\n" +
                                        ":arrow_forward: [swt_notification](https://github.com/Switty6/swt_notifications)\n" +
                                        ":arrow_forward: [Grew Licenses](" + System.getenv("LINK_STORE_URL") + "/comming-soon\")\n",
                                false),
                        new Field("Price", "12,50€ (+VAT)", true),
                        new Field("Current Version", "v1.4.1", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_drivingschool/1-readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/comming-soon", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
