package src.main.java.view;

import src.main.java.controller.*;
import src.main.java.model.entity.Order;
import src.main.java.model.entity.Restaurant;
import src.main.java.model.entity.User;

import java.util.List;

import static src.main.java.util.AdditionalUtils.formatBarrier;
import static src.main.java.util.ColorUtils.formatColor;
import static src.main.java.view.ValidationView.checkInt;
import static src.main.java.view.ValidationView.checkString;

public class CustomerView {
    public void displayMainMenu() {
        BasicView bv = new BasicView();

        System.out.println(formatBarrier("Selamat datang di BinarFud"));
        System.out.println("1. Pilih restaurant");
        System.out.println("2. Lihat order history");
        System.out.println("3. Ubah profile");
        System.out.println("4. Log out");
        bv.printExitChoice();
    }

    public void displayRestaurantsMenu() {
        RestaurantController rc = new RestaurantController();
        BasicView bv = new BasicView();

        System.out.println(formatBarrier("Pilihan Restaurant"));
        bv.printlnColor("Silahkan pilih restaurant :", "bold");
        rc.displayRestaurantList();
        System.out.println("0. Kembali ke halaman utama");
    }

    public void displayMenuItemsMenu(Restaurant restaurant) {
        MenuItemController mic = new MenuItemController();
        BasicView bv = new BasicView();

        System.out.println(formatBarrier("Pilihan Menu"));

        bv.printlnColor("Silahkan pilih makanan :", "bold");
        mic.displayMenuItemList(restaurant);
        System.out.println("99. Pesan dan bayar");
        System.out.println("100. Kembali ke halaman restaurant");
        bv.printExitChoice();
    }

    public void displayQtyMenu(Restaurant restaurant, int choice) {
        MenuItemController mic = new MenuItemController();

        System.out.println(formatBarrier("Berapa pesanan anda"));

        mic.displayMenuItem(restaurant, choice);
        System.out.println("(input 0 untuk kembali)");
        System.out.println();
    }

    public String askSize(List<String> sizeList) {
        return checkString("size => ", sizeList, true).toUpperCase();
    }

    public int askQty() {
        return checkInt("qty => ", true);
    }

    public void displayTotalMenu(Order order) {
        OrderController oc = new OrderController();

        System.out.println(formatBarrier("Konfirmasi & Pembayaran"));

        System.out.println(oc.displayTotalOrderList(order));

        System.out.println("Catatan tambahan: " +
                (order.getNotes() == null || order.getNotes().isBlank() ? "-\n" : "\n" + order.getNotes()));

        System.out.println("""
                1. Konfirmasi dan Bayar
                2. Kembali ke menu
                3. Ubah pesanan
                4. Kosongkan pesanan
                5. Tulis keterangan pesanan
                0. Keluar aplikasi
                """);
    }

    public void displayConfirmationMenu() {
        System.out.println(formatBarrier("Mohon masukkan input pilihan Anda"));

        System.out.println("(Y) untuk lanjut");
        System.out.println("(N) untuk keluar");
    }

    public void displayNotesMenu() {
        System.out.println(formatBarrier("Keterangan Pesanan"));
        System.out.println("Catatan: (ketik '0' pada baris terakhir\nuntuk mengakhiri catatan)");
    }

    public void displayEditMenu(Order order) {
        OrderController oc = new OrderController();

        System.out.println(formatBarrier("Ubah Pesanan"));

        System.out.println(oc.displayTotalOrderList(order));

        System.out.println("""
        1. Ubah detail pesanan
        2. Menghapus pesanan
        0. Kembali
        """);
    }

    public void displayReceiptMenu(Order order) {
        OrderController oc = new OrderController();
        System.out.println(oc.displayReceipt(order));
    }

    public void displayOrderHistoryMenu(User user) {
        OrderView ov = new OrderView();

        System.out.println(formatBarrier("History Order"));
        System.out.println(formatColor("Pilih detail order yang ingin dicek:", "bold"));
        ov.displayOrderList(user.getOrderList());
        System.out.println();
        System.out.println("(input 0 untuk kembali)");
    }

    public void displayOrderHistoryDetails(User user, int choice) {
        OrderController oc = new OrderController();

        System.out.println(formatBarrier("Detail Order"));
        System.out.print(oc.displayTotalOrderList(user.getOrderList().get(choice-1)));
    }
}
