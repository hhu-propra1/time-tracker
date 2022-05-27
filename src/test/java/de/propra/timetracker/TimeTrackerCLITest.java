package de.propra.timetracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class TimeTrackerCLITest {

    TimeTrackerCLI timeTrackerCLI;

    @BeforeEach
    void init() throws IOException {
        CSV csv = new CSV(false);
        this.timeTrackerCLI = new TimeTrackerCLI(csv);
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

    @Test
    @DisplayName("--sum-of-project summiert alle Einträge eines bestimmten events und gibt sie aus.")
    void readCLI4() throws IOException {
        CLIStatus cliStatus = timeTrackerCLI.readCLI(new String[]{"--sum-of-project", "projektname"});
        assertThat(cliStatus).isEqualTo(CLIStatus.SUM_MINUTES);
    }

    @Test
    @DisplayName("--sum-of-date summiert alle Einträge eines bestimmten events und gibt sie aus.")
    void readCLI5() throws IOException {
        CLIStatus cliStatus = timeTrackerCLI.readCLI(new String[]{"--sum-of-date", "2022-05-10"});
        assertThat(cliStatus).isEqualTo(CLIStatus.SUM_MINUTES);
    }

    @Test
    @DisplayName("--add fügt neuen Eintrag hinzu.")
    void readCLI6() throws IOException {
        String[] arguments = {
                "--add",
                "2022-05-11,90,ProPra1,\"Stream #4\""
        };
        CLIStatus cliStatus = timeTrackerCLI.readCLI(arguments);
        assertThat(cliStatus).isEqualTo(CLIStatus.ADD_ENTRY);
    }

    @Test
    @DisplayName("--table gibt alle Einträge in einer Tabelle aus.")
    void readCLI7() throws IOException {
        CLIStatus cliStatus = timeTrackerCLI.readCLI(new String[]{"--table"});
        assertThat(cliStatus).isEqualTo(CLIStatus.SHOW_TABLE);
    }

    @Test
    @DisplayName("--table-of-project gibt alle Einträge eines Projekts in einer Tabelle aus.")
    void readCLI8() throws IOException {
        CLIStatus cliStatus = timeTrackerCLI.readCLI(new String[]{"--table-of-project", "projektname"});
        assertThat(cliStatus).isEqualTo(CLIStatus.SHOW_TABLE);
    }

    @Test
    @DisplayName("--table-of-date gibt alle Einträge eines Datums in einer Tabelle aus.")
    void readCLI9() throws IOException {
        CLIStatus cliStatus = timeTrackerCLI.readCLI(new String[]{"--table-of-date", "2022-05-10"});
        assertThat(cliStatus).isEqualTo(CLIStatus.SHOW_TABLE);
    }

    @Test
    @DisplayName("--add today fügt neuen Eintrag hinzu.")
    void readCLI10() throws IOException {
        String[] arguments = {
                "--add",
                "today,90,ProPra1,\"Stream #4\""
        };
        CLIStatus cliStatus = timeTrackerCLI.readCLI(arguments);
        assertThat(cliStatus).isEqualTo(CLIStatus.ADD_ENTRY);
    }
}
