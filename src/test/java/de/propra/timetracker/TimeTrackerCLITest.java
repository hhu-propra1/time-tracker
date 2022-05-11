package de.propra.timetracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class TimeTrackerCLITest {

    TimeTrackerCLI timeTrackerCLI;

    @BeforeEach
    void init() {
        this.timeTrackerCLI = new TimeTrackerCLI();
    }

    @Test
    @DisplayName("Leerer Aufruf, zeige Hilfe an.")
    void readCLI1() throws IOException {
        CLIStatus cliStatus = timeTrackerCLI.readCLI(new String[]{});
        assertThat(cliStatus).isEqualTo(CLIStatus.HELP);
    }

    @Test
    @DisplayName("-h, zeige Hilfe an.")
    void readCLI2() throws IOException {
        // Act
        CLIStatus cliStatus = timeTrackerCLI.readCLI(new String[]{"-h"});
        // Assert
        assertThat(cliStatus).isEqualTo(CLIStatus.HELP);
    }

    @Test
    @DisplayName("--sum summiert alle Einträge und gibt sie aus.")
    void readCLI3() throws IOException {
        CLIStatus cliStatus = timeTrackerCLI.readCLI(new String[]{"--sum"});
        assertThat(cliStatus).isEqualTo(CLIStatus.SUM_MINUTES);
    }
}