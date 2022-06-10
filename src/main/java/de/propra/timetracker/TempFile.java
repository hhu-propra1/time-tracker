package de.propra.timetracker;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.io.IOUtils.close;

public class TempFile {
    private static final Path DEFAULT_PATH_TO_TEMP = Paths.get(System.getProperty("user.home"), ".config", "time-tracker", "temp.txt");

    static void write(String project){
        long minutes = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
        String string = LocalDate.now()+"\n"+ minutes +"\n"+ project;
        try {
            if(!Files.exists(DEFAULT_PATH_TO_TEMP)){
                Files.createDirectories(DEFAULT_PATH_TO_TEMP.getParent());
                Files.createFile(DEFAULT_PATH_TO_TEMP);
                File file = new File(String.valueOf(DEFAULT_PATH_TO_TEMP));
                FileUtils.writeStringToFile(file, string, "UTF-8");
            } else{
                System.out.println("Bitte --stop <Beschreibung> erst aufrufen, bevor --start <Projekt>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static List<String> read() {
        List<String> lines;

        try {
            FileReader file = new FileReader(String.valueOf(DEFAULT_PATH_TO_TEMP));
            lines = IOUtils.readLines(file);
            close(file);
            Files.delete(DEFAULT_PATH_TO_TEMP);
        } catch (IOException e) {
            e.printStackTrace();
            lines = List.of();
        }
        return lines;
    }
}
