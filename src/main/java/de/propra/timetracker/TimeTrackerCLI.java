package de.propra.timetracker;

import org.apache.commons.cli.*;

import java.io.IOException;

import static de.propra.timetracker.CheckDate.*;


enum CLIStatus {
    HELP, SUM_MINUTES, ADD_ENTRY, ERROR, SHOW_TABLE, START, STOP
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
        Option addOption = new Option("a", "add", true, "Füge neuen Eintrag in die Datenbank hinzu. *(oder <today> anstatt von <YYYY-MM-DD> und das Datum wird automatisch ermittelt.)");
        addOption.setArgs(4);
        addOption.setArgName("YYYY-MM-DD*>,<min>,<project>,<\"description\"");
        addOption.setValueSeparator(',');
        options.addOption(addOption);
        Option sumOption = new Option(null, "sum-of-project", true, "Summiere eingegebene Einträge eines bestimmten Projektes");
        sumOption.setArgName("project");
        options.addOption(sumOption);
        Option sumOptionDate = new Option(null, "sum-of-date", true, "Summiere eingegebene Einträge eines bestimmten Datums");
        sumOptionDate.setArgName("YYYY-MM-DD");
        options.addOption(sumOptionDate);
        Option tableOption = new Option(null, "table-of-project", true, "Zeige Tabelle aller Einträge eines bestimmten Projektes");
        tableOption.setArgName("project");
        options.addOption(tableOption);
        Option tableOptionDate = new Option(null, "table-of-date", true, "Zeige Tabelle aller Einträge eines bestimmten Datums");
        tableOptionDate.setArgName("YYYY-MM-DD");
        options.addOption(tableOptionDate);
        Option trackStart = new Option(null, "start", true, "Starte das automatisches Tracking der Projektzeit");
        trackStart.setArgName("project");
        options.addOption(trackStart);
        Option trackStop = new Option(null, "stop", true, "Beende das automatisches Tracking der Projektzeit");
        trackStop.setArgName("\"description\"");
        options.addOption(trackStop);


        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("s")) {
                int minutes = Calculations.sumMinutes(csv.readCSV());
                System.out.printf("Summe: %d Minuten", minutes);
                return CLIStatus.SUM_MINUTES;
            } else if (cmd.hasOption("sum-of-project")) {
                String projekt = cmd.getOptionValue("sum-of-project");
                int minutes = Calculations.sumMinutesOfProjekt(csv.readCSV(), projekt);
                System.out.printf("Summe: %d Minuten in %s", minutes, projekt);
                return CLIStatus.SUM_MINUTES;
            } else if (cmd.hasOption("sum-of-date")) {
                String datum = cmd.getOptionValue("sum-of-date");
                if(!isValidDate(datum)) {
                    System.out.println("Ungültiges Datum");
                    return CLIStatus.ERROR;
                }
                int minutes = Calculations.sumMinutesOfDate(csv.readCSV(), datum);
                System.out.printf("Summe: %d Minuten am %s", minutes, datum);
                return CLIStatus.SUM_MINUTES;
            } else if (cmd.hasOption("a")) {
                String[] optionValues = cmd.getOptionValues("a");
                String datum = optionValues[0].equals("today") ? java.time.LocalDate.now().toString() : optionValues[0];
                if(!isValidDate(datum)) {
                    System.out.println("Ungültiges Datum");
                    return CLIStatus.ERROR;
                }
                Event event = new Event(datum, Integer.parseInt(optionValues[1]), optionValues[2], optionValues[3]);
                csv.appendRow(event.asList());
                return CLIStatus.ADD_ENTRY;
            } else if (cmd.hasOption("t")) {
                TablePrinter.printTable(csv.readCSV());
                return CLIStatus.SHOW_TABLE;
            } else if (cmd.hasOption("table-of-project")) {
                String projekt = cmd.getOptionValue("table-of-project");
                TablePrinter.printTableOfProject(csv.readCSV(),projekt);
                return CLIStatus.SHOW_TABLE;
            } else if (cmd.hasOption("table-of-date")) {
                String datum = cmd.getOptionValue("table-of-date");
                if(!isValidDate(datum)) {
                    System.out.println("Ungültiges Datum");
                    return CLIStatus.ERROR;
                }
                TablePrinter.printTableOfDate(csv.readCSV(),datum);
                return CLIStatus.SHOW_TABLE;
            } else if (cmd.hasOption("start")) {
                String project = cmd.getOptionValue("start");
                Tracking.start(project);
                return CLIStatus.START;
            } else if (cmd.hasOption("stop")) {
                String description = cmd.getOptionValue("stop");
                Event event = Tracking.stop(description);
                csv.appendRow(event.asList());
                return CLIStatus.STOP;
            }
        } catch (ParseException e) {
            hilfe();
            return CLIStatus.ERROR;
        }

        hilfe();
        return CLIStatus.HELP;
    }
}
