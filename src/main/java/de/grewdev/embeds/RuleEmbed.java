package de.grewdev.embeds;

import de.grewdev.utils.TimeStamper;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;

public class RuleEmbed extends MessageEmbed {

    public RuleEmbed() {
        super(
                null,
                "Rules",
                null,
                null,
                null,
                Color.DARK_GRAY.getRGB(),
                null,
                null,
                null,
                null,
                new Footer("Last update: " + TimeStamper.getTimestamp(), null, null),
                null,
                Arrays.asList(
                        new Field("§1.0 Discord Usage Guidelines and Basic Law", "The general Discord-usage-guidelines and the German Basic Law for the Federal Republic of Germany must be\n" +
                                "be observed on this Discord.", false),
                        new Field("§2.0 Language", "On the Discord servers German and English are the main languages.\n" +
                                "All other languages are to be omitted.", false),
                        new Field("§2.1 Friendly interaction", """
                                On this server you should always be friendly and respectful.
                                In case of problems, contact the other party or the team members first.
                                Verbal or written insults or statements are to be refrained from.""", false),
                        new Field("§2.2 Threats, Sexual Abuse, Harassment/ Bullying", """
                                Lewd remarks/pictures, sexual harassment, sexual exploitation of minors and or pornographic content of any kind are forbidden and prohibited here.
                                An offense will be punished with immediate expulsion from the server.

                                Harassment and bullying are prohibited in any way.
                                This includes threatening to harm a group of people or the property of others.""", false),
                        new Field("§3.0 Username", "The name on the Discord must not violate §§1 and 2.", false),
                        new Field("§4.0 Spamming", "The spamming and writing in CAPS is to be refrained from in any form", false),
                        new Field("§5.0 Bot commands", "Bots may only be addressed in designated channels.", false),
                        new Field("§6.0 Discord rights", "Discord rights are not given randomly, but always serve a specific reason.\n" +
                                "If rights are needed, the supporters can be contacted (DM Allowed).", false),
                        new Field("§7.0 Support-system", "For support requests please use the ticket system. And observe its use.", false),
                        new Field("§7.1 Writing to team members", "Writing to team members with support requests is prohibited.\n" +
                                "This includes all communication methods like Discord, Whatsapp, calling etc.", false),
                        new Field("§7.2 Exceptions §11.1", "A member may be written to only after explicit instruction of the respective team member.\n" +
                                "Therefore, §7.1 Writing to team members is not applicable for these points.", false),
                        new Field("§8.0 Verification", """
                                Confirm these rules by writing "!accept" in this channel.
                                This confirms that you have read and understood the rules.
                                If you have any questions, use the support system.""", false),
                        new Field("§9.0 Gray area", "Exploitation of gaps/grey areas in the rules and regulations must be refrained from and reported.", false),
                        new Field("§10.0 Obligation to report", "Every user on the server is obliged to report rule violations immediately.", false),
                        new Field("§11.0 Rulebook Addition", """
                                The Head of Grew Development Network reserves the right to make changes to the rules at any time.
                                Changes of the server rules come into force immediately with publication.
                                Every member is obliged to keep himself up to date.
                                "Ignorance does not protect from punishment.\"""", false)
                )
        );
    }
}
