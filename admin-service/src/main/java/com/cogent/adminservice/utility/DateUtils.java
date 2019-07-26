package com.cogent.adminservice.utility;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author smriti on 7/23/19
 */
public class DateUtils {
    public static long getTimeInMillisecondsFromLocalDate() {
        LocalDateTime localDate = LocalDateTime.now();
        return Timestamp.valueOf(localDate).getTime();
    }
}
