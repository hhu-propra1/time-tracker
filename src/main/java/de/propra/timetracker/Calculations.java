package de.propra.timetracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Calculations {
    static int sumMinutes(List<Event> events) {
        return events.stream()
                .mapToInt(Event::minuten)
                .sum();
    }

    static int sumMinutesOfProjekt(List<Event> events, String projekt) {
        return events.stream()
                .filter(en -> en.projekt().equals(projekt))
                .mapToInt(Event::minuten)
                .sum();
    }

    static int sumMinutesOfDate(List<Event> events, String datum) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        String dateString;
        try {
            date = format.parse(datum);
            dateString = format.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return events.stream()
                .filter(e -> e.datum().equals(dateString))
                .mapToInt(Event::minuten)
                .sum();
    }
}
