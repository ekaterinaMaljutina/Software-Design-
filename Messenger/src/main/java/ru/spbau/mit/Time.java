package ru.spbau.mit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Time {

    private static final DateTimeFormatter FORMAT_TIME = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static final String getCurrentData() {
        LocalDateTime now = LocalDateTime.now();
        return FORMAT_TIME.format(now);
    }

}
