package de.propra.timetracker;

import java.text.ParseException;

public class CheckDate {
    static boolean isValidDate(String datum) {
        try {
            Calculations.formatter.parse(datum);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
