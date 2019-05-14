package org.onosproject.utils;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class Tools {

    private Tools() {}

    /**
     * Creates OffsetDateTime instance from epoch milliseconds,
     * using system default time zone.
     * Note: This function is copied from org.onlab.util.Tools
     *
     * @param epochMillis to convert
     * @return OffsetDateTime
     */
    public static OffsetDateTime defaultOffsetDataTime(long epochMillis) {
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(epochMillis),
                ZoneId.systemDefault());
    }
}
