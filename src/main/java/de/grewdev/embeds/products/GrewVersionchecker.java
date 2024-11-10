package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewVersionchecker extends MessageEmbed {

    public GrewVersionchecker() {
        super(
                null,
                "Grew Versionchecker",
                """
                        The Grew Versionchecker is a crucial script for the Grew Development Network.
                        It ensures that other scripts are up to date and provides notifications in the server console if outdated versions are detected.
                        With this script, you are always one step ahead and save yourself the hassle of constantly searching for updates manually!
                        If you have any questions, feel free to open an Ticket :)""",
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                new Thumbnail(System.getenv("LINK_PRODUCT_THUMBNAIL"), null, 0, 0),
                null,
                null,
                null,
                null,
                null,
                Arrays.asList(
                        new Field("Requirements",
                                ":name_badge: No Requirements",
                                false),
                        new Field("Price", "2,25€ (+VAT)", true),
                        new Field("Current Version", "v1.2.2", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_versionchecker/readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/6484290", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}
