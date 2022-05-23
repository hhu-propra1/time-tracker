package de.propra.timetracker;

import net.steppschuh.markdowngenerator.table.Table;
import java.util.List;

public class TablePrinter {

    static void printTable(List<Event> events) {
        Table.Builder tableBuilder = getBuilder();

        events.forEach(e -> tableBuilder.addRow(e.datum(),e.minuten(),e.projekt(),e.beschreibung()));
        System.out.println(tableBuilder.build());
    }

    static void printTableOf(List<Event> events, String argument) {
        String[] date = argument.split("");
        Table.Builder tableBuilder = getBuilder();
        if(date[4].equals("-") && date[7].equals("-") && date.length==10){
            events.stream().filter(e -> e.datum().equals(argument)).forEach(e -> tableBuilder.addRow(e.datum(),e.minuten(),e.projekt(),e.beschreibung()));
            System.out.println(tableBuilder.build());
        }
        else {
            events.stream().filter(e -> e.projekt().equals(argument)).forEach(e -> tableBuilder.addRow(e.datum(),e.minuten(),e.projekt(),e.beschreibung()));
            System.out.println(tableBuilder.build());
        }
    }

    private static Table.Builder getBuilder() {
        return new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("Datum", "Minuten", "Projekt", "Beschreibung");
    }
}

