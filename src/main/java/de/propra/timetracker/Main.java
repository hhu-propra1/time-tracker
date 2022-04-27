package de.propra.timetracker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        readCSV();
    }

    private static List<Event> readCSV() throws IOException {
        try (Reader in = new FileReader("src/main/resources/tasks.csv")) {
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
