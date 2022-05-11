package de.propra.timetracker;

enum CLIStatus {
    HELP, ERROR
}

public class TimeTrackerCLI {

    public static void main(String[] args) {
        TimeTrackerCLI timeTrackerCLI = new TimeTrackerCLI();
        timeTrackerCLI.readCLI(args);
    }

    CLIStatus readCLI(String[] args) {
        return CLIStatus.HELP;
    }
}
