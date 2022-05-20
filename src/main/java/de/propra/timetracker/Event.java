package de.propra.timetracker;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

record Event(String datum, int minuten, String projekt, String beschreibung) {
    Event(CSVRecord record) {
        this(
                record.get(Spalten.DATUM),
                Integer.parseInt(record.get(Spalten.MINUTEN)),
                record.get(Spalten.PROJEKT),
                record.get(Spalten.BESCHREIBUNG)
        );
    }

    public List<String> asList() {
        return List.of(this.datum, String.valueOf(this.minuten), this.projekt, this.beschreibung);
    }
}
