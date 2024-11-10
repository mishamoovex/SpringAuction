package com.lead.service.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtil {
    public static LocalDateTime createTimestamp() {
        LocalTime localTime = LocalTime.of(12, 0);
        LocalDate localDate = LocalDate.of(2024, 1, 1);
        return LocalDateTime.of(localDate, localTime);
    }

    public static LocalDateTime createTimestamp(LocalTime localTime) {
        LocalDate localDate = LocalDate.of(2024, 1, 1);
        return LocalDateTime.of(localDate, localTime);
    }

    public static LocalDateTime createTimestamp(LocalDate localDate) {
        LocalTime localTime = LocalTime.of(12, 0);
        return LocalDateTime.of(localDate, localTime);
    }
}
