package de.propra.timetracker;

import org.apache.commons.csv.CSVRecord;

record Event(String datum, int minuten, String kurs, String beschreibung) {
    Event(CSVRecord record) {
        this(
                record.get(Spalten.DATUM),
                Integer.parseInt(record.get(Spalten.MINUTEN)),
                record.get(Spalten.KURS),
                record.get(Spalten.BESCHREIBUNG)
        );
    }
}
