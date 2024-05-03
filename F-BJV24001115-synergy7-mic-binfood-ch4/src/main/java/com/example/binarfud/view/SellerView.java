package com.example.binarfud.view;

import com.example.binarfud.model.entity.*;
import com.example.binarfud.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.binarfud.util.AdditionalUtils.formatBarrier;
import static com.example.binarfud.util.ColorUtils.COLOR_OF_ERROR;
import static com.example.binarfud.util.ColorUtils.formatColor;
import static com.example.binarfud.view.ValidationView.checkInt;
import static com.example.binarfud.view.ValidationView.checkString;

@Component
public class SellerView {
    @Autowired MenuItemView menuItemView;
    @Autowired BasicView basicView;
    @Autowired RestaurantController restaurantController;

    Scanner in = new Scanner(System.in);

    public void displayMainMenu() {
        System.out.println(formatBarrier("Selamat datang di BinarFud"));
        System.out.println("1. Tambah restaurant");
        System.out.println("2. Edit restaurant");
        System.out.println("3. Ubah profile");
        System.out.println("4. Log out");
        basicView.printExitChoice();
    }

    public void displayAddRestaurantMenu() {
        System.out.println(formatBarrier("Tambah Restaurant Baru"));
        basicView.printZeroOption();
    }

    public void displayRestaurantsMenu(User user) {
        System.out.println(formatBarrier("Pilihan Restaurant"));
        basicView.printlnColor("Silahkan pilih restaurant :", "bold");
        restaurantController.displayOpenRestaurantList(user);
        System.out.println("0. Kembali ke halaman utama");
    }

    public void displayEditRestaurantMenu(Restaurant restaurant, List<MenuItem> menu) {
        System.out.println(formatBarrier("Edit Restaurant"));

        System.out.println("Nama  : " + restaurant.getName());
        System.out.println("Lokasi: " + restaurant.getLocation());
        System.out.println("Status: " + (restaurant.isOpen() ? "Buka" : "Tutup"));
        System.out.println();

        menuItemView.displayMenuItemList(menu);
        System.out.println();

        System.out.println("1. Tambah menu item");
        System.out.println("2. Edit menu item");
        System.out.println("3. Menghapus menu item");
        System.out.println("4. Ubah detail restaurant");
        System.out.println("5. Kembali ke menu pilihan restaurant");
    }

    public String askName() {
        System.out.print("Nama: ");
        return in.nextLine();
    }

    public String askLocation() {
        System.out.print("Lokasi: ");
        return in.nextLine();
    }

    public void printNameExisted() {
        System.out.println(formatColor("Nama telah dipakai.", COLOR_OF_ERROR));
    }

    public String askFoodType(String availableFoodType) {
        System.out.print("Jenis: " + "(" + availableFoodType + ") ");
        return in.nextLine();
    }

    public void printInvalidFoodType() {
        System.out.println(formatColor("Food type invalid.", COLOR_OF_ERROR));
    }

    public int askPriceM() {
        return checkInt("Harga default/size M: ");
    }

    public boolean askWantInsertPriceS() {
        String ans = checkString("Apakah Anda ingin input harga size S? (Y/N) ", new ArrayList<>(List.of("Y", "N")), true);

        return ans.equalsIgnoreCase("Y");
    }

    public int askPriceS() {
        return checkInt("Harga size S: ");
    }

    public boolean askWantInsertPriceL() {
        String ans = checkString("Apakah Anda ingin input harga size L? (Y/N) ", new ArrayList<>(List.of("Y", "N")), true);

        return ans.equalsIgnoreCase("Y");
    }

    public int askPriceL() {
        return checkInt("Harga size L: ");
    }

    public void printZeroMeansDeleteSize() {
        System.out.println("(Enter 0 untuk hilangkan size)");
    }

    public void displayEditMenuItemMenu(MenuItem menuItem) {
        System.out.println(formatBarrier("Edit Menu Item"));
        System.out.println("Nama   : " + menuItem.getName());
        System.out.println("Tipe : " + menuItem.getType());
        System.out.println("Harga S : " + menuItem.getPriceS());
        System.out.println("Harga M : " + menuItem.getPriceM());
        System.out.println("Harga L : " + menuItem.getPriceL());
        System.out.println("""
                
                1. Ganti nama
                2. Ganti tipe
                3. Ganti harga S
                4. Ganti harga M
                5. Ganti harga L
                6. Hapus menu item
                0. Kembali ke halaman utama
                """);
    }

    public void displayEditRestaurantDetailMenu(Restaurant restaurant) {
        System.out.println(formatBarrier("Edit Detail Restaurant"));
        System.out.println("Nama   : " + restaurant.getName());
        System.out.println("Lokasi : " + restaurant.getLocation());
        System.out.println("Status : " + (restaurant.isOpen() ? "Buka": "Tutup") );
        System.out.printf("""
                
                1. Ganti nama
                2. Ganti lokasi
                3. %s
                4. Hapus restaurant
                0. Kembali ke halaman utama
                """,
                restaurant.isOpen() ? "Tutup toko" : "Buka toko");
    }
}
