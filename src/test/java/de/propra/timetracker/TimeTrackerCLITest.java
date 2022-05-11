package de.propra.timetracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TimeTrackerCLITest {

    @Test
    @DisplayName("Leerer Aufruf, zeige Hilfe an.")
    void readCLI1() {
        TimeTrackerCLI timeTrackerCLI = new TimeTrackerCLI();
        assertThat(timeTrackerCLI.readCLI(new String[]{})).isEqualTo(CLIStatus.HELP);
    }
}