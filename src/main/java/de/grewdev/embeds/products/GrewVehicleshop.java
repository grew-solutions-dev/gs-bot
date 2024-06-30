package de.grewdev.embeds.products;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class GrewVehicleshop extends MessageEmbed {

    public GrewVehicleshop() {
        super(
                null,
                "Grew Vehicleshop",
                "Grew Vehicleshop is a vehicle store where each store has its own catalog.\n" +
                        "Each catalog and store is easily and extensively customizable. \n" +
                        "The store includes moving camera with different perspectives.\n" +
                        "The purchase menu has several options with different payment functions.\n" +
                        "Vehicles can be driven directly or brought to the garage with/without extra charge (Requires Grew Garage System).\n" +
                        "You can add percentage extensions for certain purchase options.\n" +
                        "For example, for the purchase through the bank, increase the price 10%.\n\n" +
                        "If you have any questions, feel free to open an Ticket :)",
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                new Thumbnail("https://dunb17ur4ymx4.cloudfront.net/packages/images/dc253153f50c289e34b896d2ce67baa07c818ea3.png", null, 0, 0),
                null,
                null,
                null,
                null,
                null,
                Arrays.asList(
                        new Field("Requirements",
                                ":arrow_forward: [es_extendet](https://github.com/esx-framework/esx_core)\n" +
                                        ":arrow_forward: [oxmysql](https://github.com/overextended/oxmysql)\n" +
                                        ":arrow_forward: [NativeUILua by Lua](https://github.com/grew-development/NativeUILua)\n" +
                                        ":arrow_forward: [swt_notification](https://github.com/Switty6/swt_notifications)\n" +
                                        ":arrow_forward: [Grew Garagesystem](https://store.grew-development.de/package/5486208)\n" +
                                        ":arrow_forward: [Grew esxAddon](https://store.grew-development.de/package/5454068)\n",
                                false),
                        new Field("Price", "5,00€ (+VAT)", true),
                        new Field("Current Version", "v1.1.1", true),
                        new Field("WIKI", System.getenv("LINK_WIKI_URL") + "/grew_vehicleshop/1-readme", false),
                        new Field("Shop-Page", System.getenv("LINK_STORE_URL") + "/package/5486221", false),
                        new Field("Trailer", "coming-soon", false)
                )
        );
    }
}