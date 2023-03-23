package com.tripmate.common.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String dateTimeFormat(String dateTime) {
        String result = null;

        LocalDateTime nowDateTime = LocalDateTime.now();

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime inputDateTime = LocalDateTime.parse(dateTime, inputFormatter);

        Duration duration = Duration.between(inputDateTime, nowDateTime);
        long diffDay = duration.getSeconds()/86400;
        long diffHour = (duration.getSeconds()%86400)/3600;
        long diffMinute = (duration.getSeconds() % 3600)/60;

        if (diffDay > 1) {
            result = dateTime.substring(0, 10).replaceAll("-", ".");
        } else if (diffDay > 0) {
            result = "어제";
        } else if (diffHour > 0) {
            result = diffHour + "시간 전";
        } else if (diffHour == 0) {
            result = diffMinute + "분 전";
        }

        return result;
    }
}
