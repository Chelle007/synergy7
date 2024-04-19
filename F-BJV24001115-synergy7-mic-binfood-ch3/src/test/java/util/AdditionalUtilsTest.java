package src.test.java.util;

import src.main.java.util.AdditionalUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class AdditionalUtilsTest {
    @Test
    void formatBarrier() {
        String expected = System.lineSeparator() +
                "===============================================" + System.lineSeparator() +
                "                   BinarFud                   " + System.lineSeparator() +
                "===============================================" + System.lineSeparator();
        String result = AdditionalUtils.formatBarrier("BinarFud");

        assertEquals(expected, result);
    }

    @Test
    void formatPrice() {
        String result = AdditionalUtils.formatPrice(10000);

        assertEquals("10.000", result);
    }

    @Test
    void formatTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 4, 16, 10, 20, 30);
        String result = AdditionalUtils.formatTime("yyyy-MM-dd HH:mm:ss", localDateTime);

        assertEquals("2024-04-16 10:20:30", result);
    }
}
