package src.main.java.view;

import static src.main.java.util.AdditionalUtils.formatBarrier;
import static src.main.java.util.ColorUtils.*;

public class BasicView {

    public void printlnColor(String output, String color) {
        System.out.println(formatColor(output, color));
    }

    public void printChoiceInvalid() {
        printlnColor("Pilihan tidak valid.", COLOR_OF_ERROR);
    }

    public void printExitChoice() {
        System.out.println("0. Keluar aplikasi");
        System.out.println();
    }

    public void displayExitMenu() {
        System.out.println(formatBarrier("Terima kasih sudah menggunakan aplikasi ini"));
    }

    public void printZeroOption() {
        System.out.println("(Enter 0 untuk kembali)");
    }
}
