package org.burgers.github_android;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.Date;

public class TimeUpdated {
    private long length;
    private String unit;
    private boolean isLongerThanMinute = true;

    public TimeUpdated(Date updatedAt) {
        DateTime now = DateTime.now();

        Duration duration = new Duration(new DateTime(updatedAt), now);

        long standardDays = duration.getStandardDays();
        long standardHours = duration.getStandardHours();
        long standardMinutes = duration.getStandardMinutes();
        if (standardDays > 0){
            length = standardDays;
            unit = standardDays > 1 ? "days" : "day";
        } else if (standardHours > 0){
            length = standardHours;
            unit = standardHours > 1 ? "hours" : "hour";
        } else if (standardMinutes > 0){
            length = standardMinutes ;
            unit = standardMinutes  > 1 ? "minutes" : "minute";
        } else {
            isLongerThanMinute = false;
        }
    }

    public long getLength() {
        return length;
    }

    public String getUnit() {
        return unit;
    }

    public boolean isLongerThanMinute() {
        return isLongerThanMinute;
    }
}
