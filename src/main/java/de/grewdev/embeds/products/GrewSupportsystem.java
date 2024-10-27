package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewSupportsystem extends MessageEmbed {

    public GrewSupportsystem() {
        super(
                null,
                "Grew Supportsystem",
                """
                        Grew Supportsystem is a NativeUI Reportmenu
                        Team members have a quick overview of all reports and can edit them quickly.
                        Very easy configurable config with templates of reports.
                        Direct teleport to the report and date display.

                        If you have any questions, feel free to open an Ticket :)""",
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                new Thumbnail("https://dunb17ur4ymx4.cloudfront.net/packages/images/04f423b7a55d65f63882e915e97344ba50a3dcc3.png", null, 0, 0),
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
                                        :arrow_forward: [NativeUILua](https://github.com/Grewoo/NativeUILua)
                                        :arrow_forward: [zf_dialog](https://github.com/zf-labo/zf_dialog)
                                        """,
                                false),
                        new Field("Price", "12,50€e", true),
                        new Field("Current Version", "v1.3.0", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_supportsystem/readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/5454027", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
