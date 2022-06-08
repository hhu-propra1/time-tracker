package de.propra.timetracker;

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
        return events.stream()
                .filter(e -> e.datum().equals(datum))
                .mapToInt(Event::minuten)
                .sum();
    }

}
