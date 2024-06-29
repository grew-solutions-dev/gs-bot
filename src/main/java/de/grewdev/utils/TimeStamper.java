package de.grewdev.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStamper {
    public static String getTimestamp() {
        return "[" + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss")) + "] ";
    }
}
