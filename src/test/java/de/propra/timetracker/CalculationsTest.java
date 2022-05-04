package de.propra.timetracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.propra.timetracker.Calculations.sumMinutes;
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


}