//package src.view;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import src.model.entity.MenuItem;
//import src.model.entity.Order;
//import src.model.entity.Promo;
//
//import static src.util.AdditionalUtils.*;
//import static src.util.ColorUtils.*;
//import static src.util.InitializationUtils.*;
//import static src.util.ValidationUtils.*;
//
//public class MenuView {
//    private static final Scanner in = new Scanner(System.in);
//    private static String notes = "";
//
//    private MenuView() {
//    }
//
//    public static void mainMenu() {
//        System.out.println(formatBarrier("Selamat datang di BinarFud"));
//
//        ArrayList<Promo> availablePromo = new ArrayList<>();
//        LocalDate currentDate = LocalDate.now();
//        LocalTime currentTime = LocalTime.now();
//        for (Promo promo : getPromos()) {
//            if (currentDate.isAfter(promo.getValidStartDate()) && currentDate.isBefore(promo.getValidEndDate()) &&
//                    currentTime.isAfter(promo.getValidStartTime()) && currentTime.isBefore(promo.getValidEndTime())) {
//                availablePromo.add(promo);
//            }
//        }
//        if (!availablePromo.isEmpty()) {
//            printlnColor("PROMO :", "bold");
//            for (Promo promo : getPromos()) {
//                System.out.println(promo.getName() + " | " + promo.getDescription());
//            }
//            System.out.println();
//        }
//
//        printlnColor("Silahkan pilih makanan :", "bold");
//        MenuItemView.displayMenuItems();
//        System.out.println("99. Pesan dan Bayar");
//        System.out.println("0. Keluar aplikasi");
//        System.out.println();
//
//        while (true) {
//            int choice = checkInt("=> ");
//
//            if (choice == 0) {
//                exitMenu();
//                break;
//            } else if (choice >= 1 && choice <= getMenu().size()) {
//                quantityMenu(choice);
//                break;
//            } else if (choice == 99) {
//                if (getOrders().isEmpty()) {
//                    printlnColor("Anda belum memesan apa pun.", COLOR_OF_ERROR);
//                } else {
//                    totalMenu();
//                    break;
//                }
//            } else {
//                printChoiceInvalid();
//            }
//        }
//    }
//
//    public static void quantityMenu(int choice) {
//        System.out.println(formatBarrier("Berapa pesanan anda"));
//        System.out.println("      Menu       |    S    |    M    |    L    ");
//
//        MenuItem selectedMenuItem = getMenu().get(choice-1);
//        System.out.printf(
//                "%d. %-13s | %16s | %16s | %16s %n",
//                (choice),
//                selectedMenuItem.getName(),
//                formatColor(formatPrice(selectedMenuItem.getSizePrice("S")), COLOR_OF_PRICE),
//                formatColor(formatPrice(selectedMenuItem.getSizePrice("M")), COLOR_OF_PRICE),
//                formatColor(formatPrice(selectedMenuItem.getSizePrice("L")), COLOR_OF_PRICE));
//        System.out.println("(input 0 untuk kembali)");
//        System.out.println();
//        askSizeAndQty(selectedMenuItem);
//        mainMenu();
//    }
//
//    public static void totalMenu() {
//        System.out.println(formatBarrier("Konfirmasi & Pembayaran"));
//
//        System.out.println(getTotalOrderList(true));
//
//        System.out.printf("Keterangan pesanan: %s%n", notes.isEmpty() ? "-" : "");
//        System.out.println(notes);
//
//        System.out.println("""
//                1. Konfirmasi dan Bayar
//                2. Kembali ke menu
//                3. Ubah pesanan
//                4. Kosongkan pesanan
//                5. Tulis keterangan pesanan
//                0. Keluar aplikasi
//                """);
//
//        while (true) {
//            int choice = checkInt("=> ");
//
//            switch (choice) {
//                case 1:
//                    confirmationMenu();
//                    break;
//                case 2:
//                    mainMenu();
//                    break;
//                case 3:
//                    editMenu();
//                    break;
//                case 4:
//                    ArrayList<Order> orders = getOrders();
//                    orders.clear();
//                    notes = "";
//                    mainMenu();
//                    break;
//                case 5:
//                    notesMenu();
//                    break;
//                case 0:
//                    exitMenu();
//                    break;
//                default:
//                    printChoiceInvalid();
//                    break;
//            }
//
//            if (choice >= 0 && choice <= 5) {
//                break;
//            }
//        }
//    }
//
//    public static void confirmationMenu() {
//        System.out.println(formatBarrier("Mohon masukkan input pilihan Anda"));
//        System.out.println("(Y) untuk lanjut");
//        System.out.println("(N) untuk keluar");
//        String ans = checkString("=> ", new String[]{"Y", "N"}, true);
//
//        if (ans.equals("Y") || ans.equals("y")) {
//            receiptMenu();
//        } else {
//            totalMenu();
//        }
//    }
//
//    public static void notesMenu() {
//        System.out.println(formatBarrier("Keterangan pesanan"));
//        System.out.println("Catatan: (ketik '0' pada baris terakhir\nuntuk mengakhiri catatan)");
//        StringBuilder newNotes = new StringBuilder();
//        boolean empty = true;
//        while (true) {
//            String newLine = in.nextLine();
//            if (newLine.equals("0")) {
//                break;
//            } else {
//                newNotes.append(newLine + "\n");
//                if (!newLine.isEmpty()) {
//                    empty = false;
//                }
//            }
//        }
//        if (empty) notes = "";
//        else notes = newNotes.toString();
//        totalMenu();
//    }
//
//    public static void editMenu() {
//        System.out.println(formatBarrier("Ubah Pesanan"));
//        System.out.println(getTotalOrderList(true));
//        System.out.println("""
//        1. Ubah detail pesanan
//        2. Menghapus pesanan
//        0. Kembali
//        """);
//
//        while (true) {
//            ArrayList<Order> orders = getOrders();
//            int choice = checkInt("=> ");
//
//            switch (choice) {
//                case 1:
//                    Order order = orders.get(askOrderChoice());
//                    askSizeAndQty(order.getMenuItem());
//                    editMenu();
//                    break;
//                case 2:
//                    orders.remove(askOrderChoice());
//                    PromoUtils.resetPromo();
//                    if (orders.isEmpty()) mainMenu();
//                    else editMenu();
//                    break;
//                case 0:
//                    totalMenu();
//                    break;
//                default:
//                    printChoiceInvalid();
//                    break;
//            }
//
//            if (choice >= 0 && choice <= 2) {
//                break;
//            }
//        }
//    }
//
//    public static void receiptMenu() {
//        try {
//            int order = 1;
//            File receiptFile = new File(String.format("receipt(%s-%04d).txt", formatCurrentTime("yyyy-MMdd"), order));
//            while (receiptFile.exists()) {
//                order++;
//                receiptFile = new File(String.format("receipt(%s-%04d).txt", formatCurrentTime("yyyy-MMdd"), order));
//            }
//
//            PrintWriter receipt = new PrintWriter(receiptFile);
//            receipt.println(getReceipt(false, order));
//            receipt.close();
//
//            System.out.println(getReceipt(true, order));
//        } catch (IOException e) {
//            printlnColor("Eror saat menghasilkan struk dalam file text.", COLOR_OF_ERROR);
//        }
//    }
//
//    public static void exitMenu() {
//        System.out.println(formatBarrier("Terima kasih sudah menggunakan aplikasi ini"));
//    }
//}
