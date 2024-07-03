package de.grewdev.cmds;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface ICommandHandler {

    SlashCommandData getCommandData();
    void handleCommand(SlashCommandInteractionEvent event);

}
