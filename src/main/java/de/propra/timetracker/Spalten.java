package de.propra.timetracker;

import java.util.List;

enum Spalten {
    DATUM, MINUTEN, KURS, BESCHREIBUNG;

    public static List<String> asList() {
        return List.of(
                Spalten.DATUM.toString(),
                Spalten.MINUTEN.toString(),
                Spalten.KURS.toString(),
                Spalten.BESCHREIBUNG.toString()
        );
    }
}
