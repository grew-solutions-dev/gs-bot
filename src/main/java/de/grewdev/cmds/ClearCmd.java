package de.grewdev.cmds;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.time.OffsetDateTime;
import java.util.List;


public class ClearCmd implements ICommandHandler {

    public ClearCmd() {
    }

    public SlashCommandData getCommandData() {
        return Commands.slash("clear", "Deletes messages in this channel")
                .addSubcommands(
                        new SubcommandData("all", "Deletes a maximum of 100 messages in these channels"),

                        new SubcommandData("count", "Deletes the specified number of messages in this channel")
                                .addOption(OptionType.INTEGER, "counter", "How many messages (max 100)?", true, false)
                );
    }

    @Override
    public void handleCommand(SlashCommandInteractionEvent event) {
        if (event.getSubcommandName().equals("all")) {
            event.deferReply().setEphemeral(true).queue();
            new Thread(() -> {
                TextChannel targetChan = event.getChannel().asTextChannel();

                targetChan.getHistory().retrievePast(100).queue(msgs -> {
                    List<Message> recentMessages = msgs.stream()
                            .filter(msg -> msg.getTimeCreated().isAfter(OffsetDateTime.now().minusDays(14)))
                            .toList();

                    targetChan.deleteMessages(recentMessages).queue(
                            success -> event.getHook().sendMessage("Messages were deleted!").queue(),
                            error -> event.getHook().sendMessage("Messages could be deleted!").queue()
                    );
                });

            }).start();

        } else if (event.getSubcommandName().equals("count")) {
            event.deferReply().setEphemeral(true).queue();
            new Thread(() -> {
                TextChannel targetChan = event.getChannel().asTextChannel();
                int count;

                if (event.getOption("counter").getAsInt() <= 100 && event.getOption("counter").getAsInt() > 0) {
                    count = event.getOption("counter").getAsInt();
                } else {
                    event.getHook().sendMessage("Their number is too large or too small.").queue();
                    return;
                }

                targetChan.getHistory().retrievePast(count).queue(msgs -> {
                    List<Message> recentMessages = msgs.stream()
                            .filter(msg -> msg.getTimeCreated().isAfter(OffsetDateTime.now().minusDays(14)))
                            .toList();

                    targetChan.deleteMessages(recentMessages).queue(
                            success -> event.getHook().sendMessage("Messages were deleted!").queue(),
                            error -> event.getHook().sendMessage("Messages could be deleted!").queue()
                    );
                });

            }).start();
        }
    }
}
