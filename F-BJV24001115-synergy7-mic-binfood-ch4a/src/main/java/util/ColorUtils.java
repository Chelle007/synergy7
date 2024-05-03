package src.main.java.util;

public class ColorUtils {
    public static final String COLOR_OF_ERROR = "red";
    public static final String COLOR_OF_QTY = "blue";
    public static final String COLOR_OF_PRICE = "tosca";

    private ColorUtils() {
    }

    public static String formatColor(String output, String color) {
        String ansiColor = "\u001B[0m";
        String ansiReset = "\u001B[0m";

        ansiColor = switch (color) {
            case COLOR_OF_ERROR -> "\u001B[31m";
            case COLOR_OF_QTY -> "\u001B[34m";
            case COLOR_OF_PRICE -> "\u001B[36m";
            case "bold" -> "\033[0;1m";
            default -> ansiColor;
        };

        return (ansiColor + output + ansiReset);
    }
}
