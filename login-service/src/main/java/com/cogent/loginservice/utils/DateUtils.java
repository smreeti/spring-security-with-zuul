package com.cogent.loginservice.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateUtils {

    public static long getTimeInMillisecondsFromLocalDate() {
        LocalDateTime localDate = LocalDateTime.now();
        return Timestamp.valueOf(localDate).getTime();
    }
}
