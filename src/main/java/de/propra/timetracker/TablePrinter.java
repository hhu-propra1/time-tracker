package de.propra.timetracker;

import net.steppschuh.markdowngenerator.table.Table;

import java.util.List;
 
public class TablePrinter {

    static void printTable(List<Event> events) {
        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("Datum", "Minuten", "Projekt", "Beschreibung");

        events.forEach(e -> tableBuilder.addRow(e.datum(), e.minuten(), e.projekt(), e.beschreibung()));
        System.out.println(tableBuilder.build());
    }
}
