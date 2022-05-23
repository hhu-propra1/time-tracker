package de.propra.timetracker;

import java.util.List;

public class Calculations {
    static int sumMinutes(List<Event> events) {
        return events.stream()
                .mapToInt(Event::minuten)
                .sum();
    }

    static int sumMinutesOfProjekt(List<Event> events, String argument) {
        String[] date = argument.split("");
        if(date[4].equals("-") && date[7].equals("-") && date.length==10){
            return events.stream()
                    .filter(e -> e.datum().equals(argument))
                    .mapToInt(Event::minuten)
                    .sum();
        }
        else {
            return events.stream()
                    .filter(e -> e.projekt().equals(argument))
                    .mapToInt(Event::minuten)
                    .sum();
        }
    }
}
