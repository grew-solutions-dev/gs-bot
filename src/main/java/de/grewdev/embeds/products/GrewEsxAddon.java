package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewEsxAddon extends MessageEmbed {

    public GrewEsxAddon() {
        super(
                null,
                "Grew esxAddon",
                "Grew esxAddon adds new commands for es_extendet.\n" +
                        "Additional events are also brought by the script. For more information please read the wiki\n\n" +
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
                                ":arrow_forward: [es_extendet](https://github.com/esx-framework/esx_core)\n",
                                false),
                        new Field("Price", "2,50€ (+VAT)", true),
                        new Field("Current Version", "v1.6.1", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_esxaddon/1-readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/5454068", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
