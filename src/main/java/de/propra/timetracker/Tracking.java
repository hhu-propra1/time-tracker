package de.propra.timetracker;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tracking {

    static void start(String project) {
        TempFile.write(project);
    }

    static Event stop(String description) {
        long endMinutes = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
        List<String> lines = TempFile.read();
        String date = lines.get(0);
        String project = lines.get(2);
        long startMinutes = Long.parseLong(lines.get(1));
        int min = (int) (endMinutes - startMinutes);
        return new Event(date, min, project, description);
    }
}
