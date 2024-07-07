package de.grewdev.events;

import de.grewdev.cmds.ICommandHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandEventListener extends ListenerAdapter {
    private static HashMap<String, ICommandHandler> commandHandlers = new HashMap<String, ICommandHandler>();

    public void onReady(@NotNull ReadyEvent event) {
        for (ICommandHandler cmdHandler : commandHandlers.values()) {
            event.getJDA().getGuildById(System.getenv("SERVER_ID")).upsertCommand(cmdHandler.getCommandData()).queue();
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(commandHandlers.containsKey(event.getName())) {
            commandHandlers.get(event.getName()).handleCommand(event);
        } else {
            event.reply("Invalid Command!").setEphemeral(true).queue();
        }
    }

    public void registerCommand(ICommandHandler commandHandler) {
        commandHandlers.put(commandHandler.getCommandData().getName(),commandHandler);
    }
}
