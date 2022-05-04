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

}