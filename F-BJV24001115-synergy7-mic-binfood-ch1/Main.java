import model.MenuItem;
import model.Order;

import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    public static ArrayList<MenuItem> menu = new ArrayList<>();
    public static ArrayList<Order> orders = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);
    public static DecimalFormat decimalFormat = new DecimalFormat("#,##0.###");

    public static void main(String[] args) {
        initializeMenu();
        mainMenu();
    }

    // METHOD MENU
    public static void mainMenu() {
        System.out.println(formatBarrier("Selamat datang di BinarFud"));
        System.out.println("Silahkan pilih makanan :");
        for (int i = 0; i<menu.size(); i++) {
            MenuItem menuItem = menu.get(i);
            System.out.printf(
                    "%d. %-13s | %s%n",
                    (i+1),
                    menuItem.getName(),
                    formatColor(formatPrice(menuItem.getPrice()), "tosca"));
        }
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar aplikasi");
        System.out.println();

        while (true) {
            int choice = checkInt("=> ");

            if (choice == 0) {
                exitApplication();
                break;
            } else if (choice >= 1 && choice <= menu.size()) {
                quantityMenu(choice);
                break;
            } else if (choice == 99) {
                if (orders.isEmpty()) {
                    printlnColor("Anda belum memesan apa pun.", "red");
                } else {
                    confirmationMenu();
                    break;
                }
            } else {
                printlnColor("Pilihan tidak valid.", "red");
            }
        }
    }

    public static void quantityMenu(int choice) {
        System.out.println(formatBarrier("Berapa pesanan anda"));

        MenuItem selectedMenuItem = menu.get(--choice);
        System.out.printf(
                "%-16s | %s%n",
                selectedMenuItem.getName(),
                formatColor(formatPrice(selectedMenuItem.getPrice()), "tosca"));
        System.out.println("(input 0 untuk kembali)");
        System.out.println();

        while (true) {
            int qty = checkInt("qty => ");

            if (qty == 0) {
                mainMenu();
                break;
            } else if (qty > 0) {
                boolean found = false;
                for (Order order : orders) {
                    if (order.getMenuItem().equals(selectedMenuItem)) {
                        order.addQty(qty);
                        found = true;
                    }
                }
                if (!found) orders.add(new Order(selectedMenuItem, qty));
                mainMenu();
                break;
            } else {
                printlnColor("Mohon input angka positif.", "red");
            }
        }

    }

    public static void confirmationMenu() {
        System.out.println(formatBarrier("Konfirmasi & Pembayaran"));
        System.out.println(returnTotalOrder(true));
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");

        while (true) {
            int choice = checkInt("=> ");

            if (choice == 1) {
                receiptMenu();
                break;
            } else if (choice == 2) {
                mainMenu();
                break;
            } else if (choice == 0) {
                exitApplication();
                break;
            } else {
                printlnColor("Pilihan tidak valid.", "red");
            }
        }
    }

    public static void receiptMenu() {
        System.out.println(returnReceipt(true));

        try {
            File receiptFile = new File("receipt.txt");
            int fileCounter = 1;
            while (receiptFile.exists()) {
                receiptFile = new File("receipt(" + fileCounter + ").txt");
                fileCounter++;
            }

            PrintWriter receipt = new PrintWriter(receiptFile);
            receipt.println(returnReceipt(false));
            receipt.close();

        } catch (IOException e) {
            printlnColor("Eror saat menghasilkan struk dalam file text.", "red");
        }
    }

    // METHOD PEMBANTU
    // Method untuk menambahkan makanan ke menu
    public static void initializeMenu() {
        menu.add(new MenuItem("Nasi Goreng", 15000));
        menu.add(new MenuItem("Mie Goreng", 13000));
        menu.add(new MenuItem("Nasi + Ayam", 18000));
        menu.add(new MenuItem("Es Teh Manis", 3000));
        menu.add(new MenuItem("Es Jeruk", 5000));
    }

    // Method saat keluar aplikasi
    public static void exitApplication() {
        System.out.println();
        printlnColor("Terima kasih sudah menggunakan aplikasi ini.", "bold");
    }

    // Method untuk return teks struk
    public static String returnReceipt(boolean withColor) {
        return formatBarrier("BinarFud") +
                """
                
                Terima kasih sudah memesan
                di BinarFud

                Di bawah ini adalah pesanan anda
                
                """ +
                returnTotalOrder(withColor) +
                "\nPembayaran : BinarCash\n" +
                formatBarrier("Simpan struk ini sebagai\nbukti pembayaran");
    }

    // Method untuk return total order dan harga
    public static String returnTotalOrder(boolean withColor) {
        StringBuilder output = new StringBuilder();
        int totalQty = 0;
        int totalPrice = 0;

        for (Order order : orders) {
            MenuItem menuItem = order.getMenuItem();
            int price = menuItem.getPrice()*order.getQty();
            String qty = Integer.toString(order.getQty());
            totalPrice += price;
            totalQty += order.getQty();

            output.append(String.format(
                    withColor ? "%-15s %-16s %s%n" : "%-15s %-7s %s%n",
                    menuItem.getName(),
                    withColor ? formatColor(qty, "blue") : qty,
                    withColor ? formatColor(formatPrice(price), "tosca") : formatPrice(price)
            ));
        }

        output.append("-".repeat(31));
        output.append("+\n");

        output.append(String.format(
                withColor ? "%-15s %-16s %s%n" : "%-15s %-7s %s%n",
                "Total ",
                withColor ? formatColor(Integer.toString(totalQty), "blue") : Integer.toString(totalQty),
                withColor ? formatColor(formatPrice(totalPrice), "tosca") : formatPrice(totalPrice)
        ));

        return output.toString();
    }

    // Method untuk menambahkan garis
    public static String formatBarrier(String text) {
        return "\n==========================\n" + text + "\n==========================\n";
    }

    // Methods untuk print teks berwarna
    public static void printlnColor(String output, String color) {
        System.out.println(formatColor(output, color));
    }

    public static String formatColor(String output, String color) {
        String ansi_color = "\u001B[0m";
        String ansi_reset = "\u001B[0m";

        ansi_color = switch (color) {
            case "red" -> "\u001B[31m";
            case "blue" -> "\u001B[34m";
            case "tosca" -> "\u001B[36m";
            case "bold" -> "\033[0;1m";
            default -> ansi_color;
        };

        return (ansi_color + output + ansi_reset);
    }

    // Method untuk format harga
    public static String formatPrice(int price) {
        return decimalFormat.format(price).replace(",", ".");
    }

    // Method untuk cek apakah input merupakan integer
    public static int checkInt(String question) {
        while (true) {
            System.out.print(question);
            try {
                int answer = input.nextInt();
                input.nextLine();
                return answer;
            }
            catch (Exception e) {
                printlnColor("Eror. Mohon input angka dengan benar.", "red");
                input.reset();
                input.nextLine();
            }
        }
    }
}
