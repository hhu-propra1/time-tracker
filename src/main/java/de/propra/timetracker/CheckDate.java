package de.propra.timetracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CheckDate {
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    static boolean isValidDate(String datum) {
        try {
            formatter.parse(datum);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
