package de.propra.timetracker;

import java.util.List;

public class Calculations {
    static int sumMinutes(List<Event> events) {
        return events.stream()
                .mapToInt(Event::minuten)
                .sum();
    }
}
