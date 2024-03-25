package util;

import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Set;

import model.entity.MenuItem;
import model.entity.Order;

import static util.ColorUtils.*;
import static util.ValidationUtils.*;
import static util.InitializationUtils.*;

public class AdditionalUtils {
    private static final ArrayList<Order> orders = getOrders();
    private static final ArrayList<Order> freebiesOrder = getFreebiesOrder();
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.###");
    private static int totalQty = 0;
    private static int totalPrice = 0;

    private AdditionalUtils() {
    }

    // Method untuk return teks struk
    public static String getReceipt(boolean withColor, int order) {
        return formatBarrier("BinarFud") +
                "Waktu : " + getCurrentTime("yyyy-MM-dd HH:mm:ss") + "\n" +
                "Order : " + order + "\n" +
                """
                
                Terima kasih sudah memesan
                di BinarFud

                Di bawah ini adalah pesanan anda
                
                """ +
                getTotalOrderList(withColor) +
                "\nPembayaran : BinarCash\n" +
                formatBarrier("Simpan struk ini sebagai bukti pembayaran");
    }

    // Method untuk return total order dan harga
    public static String getTotalOrderList(boolean withColor) {
        PromoUtils.restartPromo();

        StringBuilder output = new StringBuilder();
        totalQty = 0;
        totalPrice = 0;

        output.append(getList(withColor, getOrders()));

        if (!freebiesOrder.isEmpty()) {
            output.append("FREE\n");
            output.append(getList(withColor, getFreebiesOrder()));
        }

        output.append("-".repeat(35));
        output.append("+\n");

        output.append(String.format(
                withColor ? "%-21s %-16s %s%n" : "%-19s %-7s %s%n",
                "Total ",
                withColor ? formatColor(Integer.toString(totalQty), COLOR_OF_QTY) : Integer.toString(totalQty),
                withColor ? formatColor(formatPrice(totalPrice), COLOR_OF_PRICE) : formatPrice(totalPrice)
        ));

        return output.toString();
    }

    public static String getList(boolean withColor, List<Order> list) {
        StringBuilder output = new StringBuilder();
        String format = "%-19s %-7s %s%n";
        if (withColor) format = "%-21s %-16s %s%n";

        for (int i = 0; i<list.size(); i++) {
            Order order = list.get(i);
            MenuItem menuItem = order.getMenuItem();
            int price = order.getPrice()*order.getQty();
            String qty = Integer.toString(order.getQty());
            totalPrice += price;
            totalQty += order.getQty();

            output.append(String.format(
                    format,
                    (i+1) + ". " + menuItem.getName() + " (" + order.getSize() + ")",
                    withColor ? formatColor(qty, COLOR_OF_QTY) : qty,
                    withColor ? formatColor(formatPrice(price), COLOR_OF_PRICE) : formatPrice(price)
            ));
        }

        return output.toString();
    }

    // Method untuk return tanggal dan waktu
    public static String getCurrentTime(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
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
        if (price == null) {
            return "   -   ";
        } else {
            return decimalFormat.format(price).replace(",", ".");
        }
    }

    // Method untuk meminta input nomor dari order list
    public static int askOrderChoice() {
        while (true) {
            int choice = checkInt("Nomor order: ") - 1;
            if (choice >= 0 && choice < orders.size()) {
                return choice;
            } else {
                printlnColor("Pilihan tidak valid.", COLOR_OF_ERROR);
            }
        }
    }

    // Method untuk meminta input qty
    public static void askSizeAndQty(MenuItem menuItem) {
        Set<String> keySet = menuItem.getAvailableSize().keySet();
        ArrayList<String> sizeList = new ArrayList<>(keySet);
        sizeList.add("0");
        String size = checkString("size => ", sizeList.toArray(new String[0]), true).toUpperCase();
        if (size.equals("0")) {
            return;
        }

        int qty = checkInt("qty => ", true);

        if (qty != 0) {
            for (Order order : getOrders()) {
                if (order.getMenuItem().equals(menuItem) && order.getSize().equals(size)) {
                    orders.remove(order);
                    break;
                }
            }
            getOrders().add(new Order(menuItem, size, qty));
        }
    }
}
