package de.grewdev.cmds;

import de.grewdev.embeds.CrewMemberListEmbed;
import de.grewdev.utils.manager.CrewMemberManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CrewMemberCmd implements ICommandHandler {
    private static final Logger logger = LoggerFactory.getLogger(CrewMemberCmd.class);

    public CrewMemberCmd() {}

    @Override
    public SlashCommandData getCommandData() {
        return Commands.slash("crewmember","Command to edit the crewmember DM write permissions")
                .addSubcommands(
                    new SubcommandData("add", "Adds the crewmember for the DM write permissions")
                            .addOption(OptionType.USER, "user","Which crewmembers?", true,false),

                    new SubcommandData("remove", "Removes the crewmember for the DM write permissions")
                            .addOption(OptionType.USER, "user","Which crewmembers?", true,false),

                );
    }

    @Override
    public void handleCommand(SlashCommandInteractionEvent event) {
        if (event.getSubcommandName().equals("add")) {
            Boolean result = CrewMemberManager.getInstance().addMember(event.getOption("user").getAsMember());
            Member addTargetMember = event.getOption("user").getAsMember();

            if (result) {
                event.reply("User was successfully added").setEphemeral(true).complete();
                return;
            }

            event.reply("User was not added").setEphemeral(true).complete();
            logger.error("User was not added | {} | {}", addTargetMember.getEffectiveName(), addTargetMember.getId());

        } else if (event.getSubcommandName().equals("remove")) {
            Boolean result = CrewMemberManager.getInstance().removeMember(event.getOption("user").getAsMember().getId());
            Member addTargetMember = event.getOption("user").getAsMember();

            if (result) {
                event.reply("User was successfully removed").setEphemeral(true).complete();
                return;
            }

            event.reply("User was not removed").setEphemeral(true).complete();
            logger.error("User was not removed | {} | {}", addTargetMember.getEffectiveName(), addTargetMember.getId());

    }
}
