package net.kurien.blog.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static Timestamp currentTime() {
        Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
        timestamp.setNanos(0);

        return timestamp;
    }

    public static Date addTime(long seconds) {
        return addTime(currentTime(), seconds);
    }

    public static Date addTime(Date originDate, long seconds) {
        long originTime = originDate.getTime();
        long changeTime = originTime + (seconds * 1000);

        Timestamp timestamp = new Timestamp(changeTime);
        timestamp.setNanos(0);

        return timestamp;
    }
}
