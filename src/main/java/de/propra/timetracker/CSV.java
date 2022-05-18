package de.propra.timetracker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSV {
    private static final CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
            .setHeader(Spalten.class)
            .setSkipHeaderRecord(true)
            .build();
    private static final Path DEFAULT_PATH_TO_TASKS =
            Paths.get(System.getProperty("user.home"), ".config", "time-tracker", "tasks.csv");

    private final boolean shouldWrite;

    public CSV(boolean shouldWrite) throws IOException {
        this.shouldWrite = shouldWrite;
        initCSV();
    }

    private void initCSV() throws IOException {
        if (!Files.exists(DEFAULT_PATH_TO_TASKS)) {
            Files.createDirectories(DEFAULT_PATH_TO_TASKS.getParent());
            Files.createFile(DEFAULT_PATH_TO_TASKS);
            appendRow(Spalten.asList());
        }
    }

    void appendRow(List<String> row) throws IOException {
        if (this.shouldWrite) {
            FileWriter out = new FileWriter(String.valueOf(DEFAULT_PATH_TO_TASKS), true);
            try (CSVPrinter printer = new CSVPrinter(out, csvFormat)) {
                printer.printRecord(row);
            }
        }
    }

    List<Event> readCSV() throws IOException {
        try (Reader in = new FileReader(String.valueOf(DEFAULT_PATH_TO_TASKS))) {
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
