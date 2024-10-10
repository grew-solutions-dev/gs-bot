package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewCarmanager extends MessageEmbed {

    public GrewCarmanager() {
        super(
                null,
                "Grew Carmanager",
                """
                        Grew Carmanager is a user-friendly FiveM script, offering a convenient UI menu to effortlessly manage vehicle operations such as door control,
                        seat switching, cruise control, and more, providing an enhanced and streamlined driving experience.

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
                                        :arrow_forward: [NativeUILua](https://github.com/grew-development/NativeUILua)
                                        :arrow_forward: [zf_dialog](https://github.com/zf-labo/zf_dialog)
                                        :arrow_forward: [swt_notification](https://github.com/Switty6/swt_notifications)
                                        """,
                                false),
                        new Field("Price", "7,50€ (+VAT)", true),
                        new Field("Current Version", "v1.4.4", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_carmanager/1-readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/comming-soon", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
