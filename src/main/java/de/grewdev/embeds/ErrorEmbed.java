package de.grewdev.embeds;

import ch.qos.logback.classic.spi.ILoggingEvent;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ErrorEmbed extends MessageEmbed {

    public ErrorEmbed(ILoggingEvent eventObject) {
        super(
                null,
                "Error",
                eventObject.getFormattedMessage(),
                null,
                OffsetDateTime.now(),
                Color.RED.getRGB(),
                null,
                null,
                null,
                null,
                null,
                null,
                Arrays.stream(eventObject.getCallerData())
                        .limit(1)
                        .map(stackTraceElement -> new Field(
                                "Trace: ",
                                eventObject.getLoggerName().substring(eventObject.getLoggerName().lastIndexOf('.') + 1) + ":" + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber(),
                                false
                        ))
                        .collect(Collectors.toList())
        );
    }
}