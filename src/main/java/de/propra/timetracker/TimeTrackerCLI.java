package de.propra.timetracker;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Date;

enum CLIStatus {
    HELP, SUM_MINUTES, ADD_ENTRY, ERROR, SHOW_TABLE
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
        options.addOption("t", "table", false, "Zeige Tabelle aller Einträge");
        Option addOption = new Option("a", "add", true, "Füge neuen Eintrag in die Datenbank hinzu");
        addOption.setArgs(4);
        addOption.setValueSeparator(',');
        options.addOption(addOption);
        Option sumOption = new Option(null, "sumof", true, "Summiere eingegebene Einträge eines bestimmten Projektes");
        options.addOption(sumOption);
        Option tableOption = new Option(null, "tableof", true, "Zeige Tabelle aller Einträge eines bestimmten Projektes");
        options.addOption(tableOption);


        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("s")) {
                int minutes = Calculations.sumMinutes(csv.readCSV());
                System.out.printf("Summe: %d Minuten", minutes);
                return CLIStatus.SUM_MINUTES;
            } else if (cmd.hasOption("sumof")) {
                String projekt = cmd.getOptionValue("sumof");
                int minutes = Calculations.sumMinutesOfProjekt(csv.readCSV(), projekt);
                return CLIStatus.SUM_MINUTES;
            } else if (cmd.hasOption("a")) {
                String[] optionValues = cmd.getOptionValues("a");
                String date = optionValues[0].equals("today") ? java.time.LocalDate.now().toString() : optionValues[0];
                System.out.println(date);
                Event event = new Event(date, Integer.parseInt(optionValues[1]), optionValues[2], optionValues[3]);
                csv.appendRow(event.asList());
                return CLIStatus.ADD_ENTRY;
            } else if (cmd.hasOption("t")) {
                TablePrinter.printTable(csv.readCSV());
                return CLIStatus.SHOW_TABLE;
            } else if (cmd.hasOption("tableof")) {
                String projekt = cmd.getOptionValue("tableof");
                TablePrinter.printTableOf(csv.readCSV(),projekt);
                return CLIStatus.SHOW_TABLE;
            }
        } catch (ParseException e) {
            hilfe();
            return CLIStatus.ERROR;
        }

        hilfe();
        return CLIStatus.HELP;
    }
}
