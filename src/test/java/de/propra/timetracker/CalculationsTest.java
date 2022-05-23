package de.propra.timetracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.propra.timetracker.Calculations.sumMinutes;
import static de.propra.timetracker.Calculations.sumMinutesOfProjekt;
import static org.assertj.core.api.Assertions.assertThat;

class CalculationsTest {

    @Test
    @DisplayName("Empty list gives zero minutes.")
    void test1() {
        assertThat(sumMinutes(List.of())).isZero();
    }

    @Test
    @DisplayName("One event in a list gives the event's minutes.")
    void test2() {
        Event event = new Event("2022-05-04", 30, "ProPra1", "Events schreiben implementieren");
        List<Event> events = List.of(event);
        assertThat(sumMinutes(events)).isEqualTo(30);
    }

    @Test
    @DisplayName("Two events returns the sum of their minutes.")
    void test3() {
        Event event1 = new Event("2022-05-04", 30, "ProPra1", "Events schreiben implementieren");
        Event event2 = new Event("2022-05-05", 30, "ProPra2", "Events schreiben implementieren");
        List<Event> events = List.of(event1, event2);
        assertThat(sumMinutes(events)).isEqualTo(60);
    }

    @Test
    @DisplayName("Sum of events with same name.")
    void test4() {
        Event event = new Event("2022-05-04", 30, "ProPra1", "Events schreiben implementieren");
        List<Event> events = List.of(event, event);
        assertThat(sumMinutesOfProjekt(events, "ProPra1")).isEqualTo(60);
    }

    @Test
    @DisplayName("Sum of events with same date.")
    void test5() {
        Event event1 = new Event("2022-05-10", 30, "ProPra1", "Events schreiben implementieren");
        Event event2 = new Event("2022-05-10", 30, "ProPra2", "Events schreiben implementieren");
        List<Event> events = List.of(event1, event2);
        assertThat(sumMinutesOfProjekt(events, "2022-05-10")).isEqualTo(60);
    }
}
