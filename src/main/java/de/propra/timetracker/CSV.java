package de.propra.timetracker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.List;

public class CSV {
    private static final CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
            .setHeader(Spalten.class)
            .setSkipHeaderRecord(true)
            .build();
    private static final String DEFAULT_PATH_TO_TASKS =
            String.valueOf(Paths.get(System.getProperty("user.home"), ".config", "time-tracker", "tasks.csv"));

    private final String pathToCSV;
    private final boolean shouldWrite;

    public CSV(String pathToCSV, boolean shouldWrite) {
        this.pathToCSV = pathToCSV;
        this.shouldWrite = shouldWrite;
    }

    public CSV() {
        this(DEFAULT_PATH_TO_TASKS, true);
    }

    void appendEvent(Event event) throws IOException {
        if (this.shouldWrite) {
            FileWriter out = new FileWriter(pathToCSV, true);
            try (CSVPrinter printer = new CSVPrinter(out, csvFormat)) {
                printer.printRecord(event.asList());
            }
        }
    }

    List<Event> readCSV() throws IOException {
        try (Reader in = new FileReader(pathToCSV)) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(Spalten.class)
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser records = csvFormat.parse(in);
            return records.stream()
                    .map(Event::new)
                    .toList();
        }
    }
}
