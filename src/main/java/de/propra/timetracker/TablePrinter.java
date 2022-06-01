package de.propra.timetracker;

import net.steppschuh.markdowngenerator.table.Table;

import java.util.List;

public class TablePrinter {

    private static final Table.Builder tableBuilder = new Table.Builder()
            .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
            .addRow((Object[]) Spalten.values());

    static void printTable(List<Event> events) {
        events.forEach(e -> tableBuilder.addRow(e.datum(),e.minuten(),e.projekt(),e.beschreibung()));
        System.out.println(tableBuilder.build());
    }

    static void printTableOfProject(List<Event> events, String projekt) {
        events.stream().filter(e -> e.projekt().equals(projekt))
                .forEach(en -> tableBuilder.addRow(en.datum(),en.minuten(),en.projekt(),en.beschreibung()));
        System.out.println(tableBuilder.build());
    }

    static void printTableOfDate(List<Event> events, String datum) {
        events.stream().filter(d -> d.datum().equals(datum))
                .forEach(d -> tableBuilder
                        .addRow(d.datum(),d.minuten(),d.projekt(),d.beschreibung()));
        System.out.println(tableBuilder.build());
    }

}
