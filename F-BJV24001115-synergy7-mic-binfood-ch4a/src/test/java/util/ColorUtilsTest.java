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

    @Test
    void formatColor2() {
        String expected = "\u001B[34m10\u001B[0m";
        String result = ColorUtils.formatColor("10", ColorUtils.COLOR_OF_QTY);

        assertEquals(expected, result);
    }

    @Test
    void formatColor3() {
        String expected = "\u001B[36m10.000\u001B[0m";
        String result = ColorUtils.formatColor("10.000", ColorUtils.COLOR_OF_PRICE);

        assertEquals(expected, result);
    }

    @Test
    void formatColor4() {
        String expected = "\033[0;1mHello\u001B[0m";
        String result = ColorUtils.formatColor("Hello", "bold");

        assertEquals(expected, result);
    }

    @Test
    void formatColor5() {
        String expected = "\u001B[0mHello\u001B[0m";
        String result = ColorUtils.formatColor("Hello", "nothing");

        assertEquals(expected, result);
    }
}
