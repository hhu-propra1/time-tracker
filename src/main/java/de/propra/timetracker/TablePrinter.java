package de.propra.timetracker;

import net.steppschuh.markdowngenerator.table.Table;
import java.util.List;

public class TablePrinter {

    private static Table.Builder createDefaultTableBuilder(){
        return new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("Datum", "Minuten", "Projekt", "Beschreibung");
    }

    static void printTable(List<Event> events) {
        Table.Builder tableBuilder = createDefaultTableBuilder();

        events.forEach(e -> tableBuilder.addRow(e.datum(),e.minuten(),e.projekt(),e.beschreibung()));
        System.out.println(tableBuilder.build());
    }

    static void printTableOf(List<Event> events, String projekt) {
        Table.Builder tableBuilder = createDefaultTableBuilder();

        events.stream().filter(e -> e.projekt().equals(projekt)).forEach(e -> tableBuilder.addRow(e.datum(),e.minuten(),e.projekt(),e.beschreibung()));
        System.out.println(tableBuilder.build());
    }
}
