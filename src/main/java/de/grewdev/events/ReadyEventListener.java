package de.grewdev.events;

import de.grewdev.EmbedsManager;
import de.grewdev.embeds.LinksEmbed;
import de.grewdev.embeds.RuleEmbed;
import de.grewdev.embeds.products.GrewVehicleshop;
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
        embedsManager.createOrUpdateEmbed("grewVehicleshop", "productEmbeds", System.getenv("GREW_VEHICLESHOP"), new GrewVehicleshop());
    }
}
