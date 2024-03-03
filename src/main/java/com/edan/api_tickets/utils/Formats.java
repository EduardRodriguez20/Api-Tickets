package com.edan.api_tickets.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formats {
    private static final DateTimeFormatter format_date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(LocalDateTime localDateTime){
        return (localDateTime != null) ? localDateTime.format(format_date) : "";
    }

}
