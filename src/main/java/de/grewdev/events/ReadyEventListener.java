package de.grewdev.events;

import de.grewdev.utils.manager.EmbedsManager;
import de.grewdev.embeds.LinksEmbed;
import de.grewdev.embeds.RuleEmbed;
import de.grewdev.embeds.products.*;
import de.grewdev.utils.TimeStamper;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadyEventListener extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ReadyEventListener.class);

    @Override
    public void onReady(ReadyEvent event) {
        TextChannel tc = event.getJDA().getTextChannelById(System.getenv("CHAN_BOT_STATUS"));
        if (tc != null) {
            tc.sendMessage(TimeStamper.getTimestamp() + ":white_check_mark: Bot is running now!")
                    .queue();
        }

        createOrUpdateDefaultEmbeds(event.getJDA());
    }

    public void createOrUpdateDefaultEmbeds(JDA jda) {
        EmbedsManager embedsManager = EmbedsManager.getInstance(jda);

        //Fix Embed
        embedsManager.createOrUpdateEmbed("ruleEmbed", "fixedEmbeds", System.getenv("CHAN_RULES"), new RuleEmbed());
        embedsManager.createOrUpdateEmbed("linkEmbed", "fixedEmbeds", System.getenv("CHAN_LINKS"), new LinksEmbed());

        //Products Embed
        embedsManager.createOrUpdateEmbed("grewNpc", "productEmbeds", System.getenv("GREW_NPC"), new GrewNPC());
        embedsManager.createOrUpdateEmbed("grewVersionchecker", "productEmbeds", System.getenv("GREW_VERSIONCHECKER"), new GrewVersionchecker());
        embedsManager.createOrUpdateEmbed("grewPlasticsurgeon", "productEmbeds", System.getenv("GREW_PLASTICSURGEON"), new GrewPlasticsurgeon());
        embedsManager.createOrUpdateEmbed("grewEsxAddon", "productEmbeds", System.getenv("GREW_ESXADDON"), new GrewEsxAddon());
        embedsManager.createOrUpdateEmbed("grewSupportclothes", "productEmbeds", System.getenv("GREW_SUPPORTCLOTHES"), new GrewSupportclothes());
        embedsManager.createOrUpdateEmbed("grewSupportsystem", "productEmbeds", System.getenv("GREW_SUPPORTSYSTEM"), new GrewSupportsystem());
        embedsManager.createOrUpdateEmbed("grewGaragesystem", "productEmbeds", System.getenv("GREW_GARAGESYSTEM"), new GrewGaragesystem());
        embedsManager.createOrUpdateEmbed("grewVehicleshop", "productEmbeds", System.getenv("GREW_VEHICLESHOP"), new GrewVehicleshop());
        embedsManager.createOrUpdateEmbed("grewBillingsystem", "productEmbeds", System.getenv("GREW_BILLINGSYSTEM"), new GrewBillingsystem());
        embedsManager.createOrUpdateEmbed("grewCarmanager", "productEmbeds", System.getenv("GREW_CARMANAGER"), new GrewCarmanager());
        embedsManager.createOrUpdateEmbed("grewDrivingschool", "productEmbeds", System.getenv("GREW_DRIVINGSCHOOL"), new GrewDrivingschool());
        embedsManager.createOrUpdateEmbed("grewLicenses", "productEmbeds", System.getenv("GREW_LICENSES"), new GrewLicenses());
        embedsManager.createOrUpdateEmbed("grewInventory", "productEmbeds", System.getenv("GREW_INVENTORY"), new GrewInventory());
    }
}
