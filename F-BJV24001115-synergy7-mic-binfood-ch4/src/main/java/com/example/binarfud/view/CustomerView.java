package com.example.binarfud.view;

import com.example.binarfud.model.entity.*;
import com.example.binarfud.controller.*;
import com.example.binarfud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.binarfud.util.AdditionalUtils.formatBarrier;
import static com.example.binarfud.util.ColorUtils.formatColor;
import static com.example.binarfud.view.ValidationView.checkInt;
import static com.example.binarfud.view.ValidationView.checkString;

@Component
public class CustomerView {
    @Autowired BasicView basicView;
    @Autowired MenuItemController menuItemController;
    @Autowired RestaurantController restaurantController;
    @Autowired OrderController orderController;
    @Autowired OrderView orderView;

    public void displayMainMenu() {
        System.out.println(formatBarrier("Selamat datang di BinarFud"));
        System.out.println("1. Pilih restaurant");
        System.out.println("2. Lihat order history");
        System.out.println("3. Ubah profile");
        System.out.println("4. Log out");
        basicView.printExitChoice();
    }

    public void displayOpenRestaurantsMenu() {
        System.out.println(formatBarrier("Pilihan Restaurant"));
        basicView.printlnColor("Silahkan pilih restaurant :", "bold");
        restaurantController.displayOpenRestaurantList();
        System.out.println("0. Kembali ke halaman utama");
    }

    public void displayMenuItemsMenu(Restaurant restaurant, int page) {
        System.out.println(formatBarrier("Pilihan Menu"));

        basicView.printlnColor("Silahkan pilih makanan :", "bold");
        menuItemController.displayMenuItemList(restaurant, page);
        System.out.println("99. Pesan dan bayar");
        System.out.println("100. Kembali ke halaman restaurant");
        System.out.println("(Input '101' untuk ke halaman sebelumnya,\n'102' untuk ke halaman berikutnya)");
        basicView.printExitChoice();
    }

    public void displayQtyMenu(Restaurant restaurant, int choice) {
        System.out.println(formatBarrier("Berapa pesanan anda"));

        menuItemController.displayMenuItem(restaurant, choice);
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
        System.out.println(formatBarrier("Konfirmasi & Pembayaran"));

        System.out.println(orderController.displayTotalOrderList(order));

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
        System.out.println(formatBarrier("Ubah Pesanan"));

        System.out.println(orderController.displayTotalOrderList(order));

        System.out.println("""
        1. Ubah detail pesanan
        2. Menghapus pesanan
        0. Kembali
        """);
    }

    public void displayReceiptMenu(Order order) {
        System.out.println(orderController.displayReceipt(order));
    }

    public void displayOrderHistoryMenu(List<Order> orders) {
        System.out.println(formatBarrier("History Order"));
        System.out.println(formatColor("Pilih detail order yang ingin dicek:", "bold"));
        orderView.displayOrderList(orders);
        System.out.println();
        System.out.println("(input 0 untuk kembali)");
    }

    public void displayOrderHistoryDetails(List<Order> orders, int choice) {
        System.out.println(formatBarrier("Detail Order"));
        System.out.print(orderController.displayTotalOrderList(orders.get(choice-1)));
    }
}
