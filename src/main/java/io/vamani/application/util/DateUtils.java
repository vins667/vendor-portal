package io.vamani.application.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate asLocalDate(Instant date) {
        return date.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Instant asStringtoInstantFirst(String date, String format) throws ParseException {
        Date selDate = new SimpleDateFormat(format).parse(date);
        Calendar c = Calendar.getInstance();   // this takes current date
        c.setTime(selDate);
        c.set(Calendar.DATE, 1);
        selDate = c.getTime();
        return selDate.toInstant();
    }

    public static Instant asStringtoInstantLast(String date, String format) throws ParseException {
        Date selDate = new SimpleDateFormat(format).parse(date);
        Calendar c = Calendar.getInstance();   // this takes current date
        c.setTime(selDate);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        selDate = c.getTime();
        return selDate.toInstant();
    }
}
