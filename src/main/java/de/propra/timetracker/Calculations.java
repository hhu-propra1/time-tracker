package de.propra.timetracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Calculations {
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

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
        return events.stream()
                .filter(e -> e.datum().equals(datum))
                .mapToInt(Event::minuten)
                .sum();
    }

    static boolean isValidDate(String datum) {
        try {
            formatter.parse(datum);
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }
}
