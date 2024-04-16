package src.util;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdditionalUtils {

    private AdditionalUtils() {
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
