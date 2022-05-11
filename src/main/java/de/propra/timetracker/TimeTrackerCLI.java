package de.propra.timetracker;

import org.apache.commons.cli.*;

import java.io.IOException;

enum CLIStatus {
    HELP, SUM_MINUTES, ERROR
}

public class TimeTrackerCLI {

    private static Options options = new Options();

    public static void main(String[] args) throws IOException {
        TimeTrackerCLI timeTrackerCLI = new TimeTrackerCLI();
        timeTrackerCLI.readCLI(args);
    }

    private void hilfe() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("time-tracker", options);
    }

    CLIStatus readCLI(String[] args) throws IOException {
        options.addOption("h", "help", false, "Zeige diese Hilfe an");
        options.addOption("s", "sum", false, "Summiere eingegebene Einträge");
        // --sum
        // --add 2022-05-11 90 ProPra1 "Stream #4"

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                hilfe();
                return CLIStatus.HELP;
            } else if (cmd.hasOption("s")) {
                int minutes = Calculations.sumMinutes(CSV.readCSV());
                System.out.printf("Summe: %d Minuten", minutes);
                return CLIStatus.SUM_MINUTES;
            }

        } catch (ParseException e) {
            hilfe();
            return CLIStatus.ERROR;
        }

        hilfe();
        return CLIStatus.HELP;
    }
}
