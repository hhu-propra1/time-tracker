package de.propra.timetracker;

import net.steppschuh.markdowngenerator.table.Table;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.List;

public class TablePrinter {

    static void printTable(List<Event> events) {
        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("Datum", "Minuten", "Projekt", "Beschreibung");

        events.forEach(e -> tableBuilder.addRow(e.datum(),e.minuten(),e.projekt(),e.beschreibung()));
        System.out.println(tableBuilder.build());
    }

    static void printTableOf(List<Event> events, String projekt) {
        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("Datum", "Minuten", "Projekt", "Beschreibung");

        //events.forEach(e -> tableBuilder.addRow(e.datum(),e.minuten(),e.projekt(),e.beschreibung()));
        for (Event event : events) {
            if(event.projekt().equals(projekt)){
                tableBuilder.addRow(event.datum(),event.minuten(), event.projekt(),event.beschreibung());
            }
        }
        System.out.println(tableBuilder.build());
    }
}
