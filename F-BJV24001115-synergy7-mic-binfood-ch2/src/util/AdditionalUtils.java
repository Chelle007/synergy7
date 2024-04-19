package src.main.java.util;

import static src.main.java.util.ColorUtils.COLOR_OF_ERROR;
import static src.main.java.util.ColorUtils.printlnColor;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdditionalUtils {

    private AdditionalUtils() {
    }

    // Method untuk print pilihan tidak valid
    public static void printChoiceInvalid() {
        printlnColor("Pilihan tidak valid.", COLOR_OF_ERROR);
    }

    // Method untuk menambahkan garis
    public static String formatBarrier(String text) {
        int totalWidth = 47;
        int textWidth = text.length();
        int padding = (totalWidth - textWidth) / 2;
        return String.format("%n%s%n%s%n%s%n",
                "=".repeat(totalWidth),
                " ".repeat(padding) + text + " ".repeat(padding),
                "=".repeat(totalWidth));
    }

    // Method untuk format harga
    public static String formatPrice(Integer price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.###");
        if (price == null) {
            return "   -   ";
        } else {
            return decimalFormat.format(price).replace(",", ".");
        }
    }

    // Method untuk format tanggal dan waktu saat ini
    public static String formatCurrentTime(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }
}
