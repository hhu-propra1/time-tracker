package de.propra.timetracker;

import org.apache.commons.cli.*;

enum CLIStatus {
    HELP, ERROR
}

public class TimeTrackerCLI {

    private static Options options = new Options();

    public static void main(String[] args) {
        TimeTrackerCLI timeTrackerCLI = new TimeTrackerCLI();
        timeTrackerCLI.readCLI(args);
    }

    private void hilfe() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("time-tracker", options);
    }

    CLIStatus readCLI(String[] args) {
        options.addOption("h", "help", false, "Zeige diese Hilfe an");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                hilfe();
                return CLIStatus.HELP;
            }

        } catch (ParseException e) {
            hilfe();
            return CLIStatus.ERROR;
        }

        hilfe();
        return CLIStatus.HELP;
    }
}
