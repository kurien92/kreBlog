package net.kurien.blog.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static Date currentTime() {
        return Calendar.getInstance().getTime();
    }

    public static Date addTime(long seconds) {
        return addTime(currentTime(), seconds);
    }

    public static Date addTime(Date originDate, long seconds) {
        long originTime = originDate.getTime();
        long changeTime = originTime + (seconds * 1000);

        return new Date(changeTime);
    }
}
