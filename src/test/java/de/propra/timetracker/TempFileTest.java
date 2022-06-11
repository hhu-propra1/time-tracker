package de.propra.timetracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TempFileTest {

    @Test
    @DisplayName("Test Write and Read")
    void test() {

        String project = "ProPra1";
        String date = LocalDate.now().toString();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
        List<String> list;

        TempFile.write(project);
        list=TempFile.read();

        assertThat(list).isEqualTo(List.of(date, String.valueOf(minutes), project));
    }
}
