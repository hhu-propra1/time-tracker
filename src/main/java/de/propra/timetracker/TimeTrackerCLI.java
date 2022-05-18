package de.propra.timetracker;

import org.apache.commons.cli.*;

import java.io.IOException;

enum CLIStatus {
    HELP, SUM_MINUTES, ADD_ENTRY, ERROR
}

public class TimeTrackerCLI {

    private static final Options options = new Options();
    private final CSV csv;

    public TimeTrackerCLI(CSV csv) {
        this.csv = csv;
    }

    public static void main(String[] args) throws IOException {
        TimeTrackerCLI timeTrackerCLI = new TimeTrackerCLI(new CSV(true));
        timeTrackerCLI.readCLI(args);
    }

    private void hilfe() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("time-tracker", options);
    }

    CLIStatus readCLI(String[] args) throws IOException {
        options.addOption("h", "help", false, "Zeige diese Hilfe an");
        options.addOption("s", "sum", false, "Summiere eingegebene Einträge");
        Option newEntryOption = new Option("a", "add", true, "Füge neuen Eintrag in die Datenbank hinzu");
        newEntryOption.setArgs(4);
        newEntryOption.setValueSeparator(',');
        options.addOption(newEntryOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("s")) {
                int minutes = Calculations.sumMinutes(csv.readCSV());
                System.out.printf("Summe: %d Minuten", minutes);
                return CLIStatus.SUM_MINUTES;
            } else if (cmd.hasOption("a")) {
                String[] optionValues = cmd.getOptionValues("a");
                Event event = new Event(optionValues[0], Integer.parseInt(optionValues[1]), optionValues[2], optionValues[3]);
                csv.appendRow(event.asList());
                return CLIStatus.ADD_ENTRY;
            }
        } catch (ParseException e) {
            hilfe();
            return CLIStatus.ERROR;
        }

        hilfe();
        return CLIStatus.HELP;
    }
}
