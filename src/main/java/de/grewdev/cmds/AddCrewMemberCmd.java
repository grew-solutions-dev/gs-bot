package de.grewdev.cmds;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class AddCrewMemberCmd implements ICommandHandler {

    public AddCrewMemberCmd() {}

    @Override
    public SlashCommandData getCommandData() {
        return Commands.slash("crewmember","");
    }

    @Override
    public void handleCommand(SlashCommandInteractionEvent event) {

    }
}
