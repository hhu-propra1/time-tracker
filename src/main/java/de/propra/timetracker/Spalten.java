package de.propra.timetracker;

import java.util.List;

enum Spalten {
    DATUM, MINUTEN, PROJEKT, BESCHREIBUNG;

    public static List<String> asList() {
        return List.of(
                Spalten.DATUM.toString(),
                Spalten.MINUTEN.toString(),
                Spalten.PROJEKT.toString(),
                Spalten.BESCHREIBUNG.toString()
        );
    }
}
