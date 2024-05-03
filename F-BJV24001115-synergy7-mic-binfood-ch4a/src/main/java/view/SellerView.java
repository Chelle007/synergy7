package src.main.java.view;

import src.main.java.controller.RestaurantController;
import src.main.java.model.entity.MenuItem;
import src.main.java.model.entity.Restaurant;
import src.main.java.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static src.main.java.util.AdditionalUtils.formatBarrier;
import static src.main.java.util.ColorUtils.COLOR_OF_ERROR;
import static src.main.java.util.ColorUtils.formatColor;
import static src.main.java.view.ValidationView.checkInt;
import static src.main.java.view.ValidationView.checkString;

public class SellerView {
    Scanner in = new Scanner(System.in);

    public void displayMainMenu() {
        BasicView bv = new BasicView();

        System.out.println(formatBarrier("Selamat datang di BinarFud"));
        System.out.println("1. Tambah restaurant");
        System.out.println("2. Edit restaurant");
        System.out.println("3. Ubah profile");
        System.out.println("4. Log out");
        bv.printExitChoice();
    }

    public void displayRestaurantsMenu(User user) {
        RestaurantController rc = new RestaurantController();
        BasicView bv = new BasicView();

        System.out.println(formatBarrier("Pilihan Restaurant"));
        bv.printlnColor("Silahkan pilih restaurant :", "bold");
        rc.displayRestaurantList(user);
        System.out.println("0. Kembali ke halaman utama");
    }

    public void displayEditRestaurantMenu(Restaurant restaurant, List<MenuItem> menu) {
        MenuItemView miv = new MenuItemView();

        System.out.println(formatBarrier("Edit Restaurant"));

        System.out.println("Nama  : " + restaurant.getName());
        System.out.println("Lokasi: " + restaurant.getLocation());
        System.out.println("Status: " + (restaurant.isOpen() ? "Buka" : "Tutup"));
        System.out.println();

        miv.displayMenuItemList(menu);
        System.out.println();

        System.out.println("1. Tambah menu item");
        System.out.println("2. Edit menu item");
        System.out.println("3. Menghapus menu item");
        System.out.println("4. Ubah detail restaurant");
        System.out.println("5. Kembali ke menu pilihan restaurant");
    }

    public String askMenuItemName() {
        System.out.print("Nama: ");
        return in.nextLine();
    }

    public void printMenuItemNameExisted() {
        System.out.println(formatColor("Nama menu item telah dipakai.", COLOR_OF_ERROR));
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
}
