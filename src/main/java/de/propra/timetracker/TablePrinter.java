package de.propra.timetracker;

import net.steppschuh.markdowngenerator.table.Table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TablePrinter {

    static void printTable(List<Event> events) {
        Table.Builder tableBuilder = getBuilder();

        events.forEach(e -> tableBuilder.addRow(e.datum(),e.minuten(),e.projekt(),e.beschreibung()));
        System.out.println(tableBuilder.build());
    }

    static void printTableOf(List<Event> events, String argument) {
        Table.Builder tableBuilder = getBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(argument);
            events.stream().filter(e -> e.datum().equals(argument)).forEach(e -> tableBuilder.addRow(e.datum(),e.minuten(),e.projekt(),e.beschreibung()));
            System.out.println(tableBuilder.build());
        } catch (ParseException e) {
            events.stream().filter(en -> en.projekt().equals(argument)).forEach(en -> tableBuilder.addRow(en.datum(),en.minuten(),en.projekt(),en.beschreibung()));
            System.out.println(tableBuilder.build());
        }
    }

    private static Table.Builder getBuilder() {
        return new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("Datum", "Minuten", "Projekt", "Beschreibung");
    }
}

