package de.grewdev.embeds;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CrewMemberListEmbed extends MessageEmbed {

    public CrewMemberListEmbed(List<String> userIds) {
        super(
                null,
                "All Crewmember",
                "These users can write newsletters via bot.",
                null,
                OffsetDateTime.now(),
                Color.CYAN.getRGB(),
                null,
                null,
                null,
                null,
                null,
                null,
                Arrays.stream(userIds.toArray())
                        .map(userId -> new Field(
                                "UserId: " + userId,
                                "<@" + userId + ">",
                                false
                        ))
                        .collect(Collectors.toList())
        );
    }
}
