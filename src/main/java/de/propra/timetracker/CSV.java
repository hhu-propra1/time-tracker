package de.propra.timetracker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CSV {
    private static final String TASKS_CSV = "src/main/resources/tasks.csv";
    private static final CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
            .setHeader(Spalten.class)
            .setSkipHeaderRecord(true)
            .build();

    static List<Event> readCSV() throws IOException {
        try (Reader in = new FileReader(TASKS_CSV)) {
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

    static void appendEvent(Event event) throws IOException {
        FileWriter out = new FileWriter(TASKS_CSV, true);
        try (CSVPrinter printer = new CSVPrinter(out, csvFormat)) {
            printer.printRecord(event.asList());
        }
    }
}
