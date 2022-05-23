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

    static int sumMinutesOfProjekt(List<Event> events, String argument) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(argument);
        } catch (ParseException e) {
            return events.stream()
                    .filter(en -> en.projekt().equals(argument))
                    .mapToInt(Event::minuten)
                    .sum();
        }
        return events.stream()
                .filter(e -> e.datum().equals(argument))
                .mapToInt(Event::minuten)
                .sum();
    }
}
