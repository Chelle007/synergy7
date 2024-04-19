package src.test.java.util;

import src.main.java.util.ColorUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorUtilsTest {
    @Test
    void formatColor() {
        String expected = "\u001B[31mError\u001B[0m";
        String result = ColorUtils.formatColor("Error", ColorUtils.COLOR_OF_ERROR);

        assertEquals(expected, result);
    }
}
