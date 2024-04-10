package src.view;

import java.util.Scanner;

import src.controller.*;
import static src.util.AdditionalUtils.formatBarrier;
import static src.util.ColorUtils.printlnColor;
import static src.util.ValidationUtils.checkInt;
import static src.util.ValidationUtils.checkString;

public class CustomerView {
    public void displayRestaurantMenu() {
        RestaurantController rc = new RestaurantController();

        System.out.println(formatBarrier("Selamat datang di BinarFud"));
        printlnColor("Silahkan pilih restaurant :", "bold");
        rc.displayRestaurantList();

    }

    public void displayMainMenu() {
        PromoController pc = new PromoController();
        MenuItemController mic = new MenuItemController();
        CustomerController cc = new CustomerController();

        System.out.println(formatBarrier("Selamat datang di BinarFud"));

        pc.displayPromoList();

        printlnColor("Silahkan pilih makanan :", "bold");
        mic.displayMenuItemList();
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar aplikasi");
        System.out.println();

        while (true) {
            if (cc.mainMenuSelection(checkInt("=> "))) break;
        }
    }

    public void displayQtyMenu(int choice) {
        MenuItemController mic = new MenuItemController();
        CustomerController cc = new CustomerController();

        System.out.println(formatBarrier("Berapa pesanan anda"));

        mic.displayMenuItem(choice);
        System.out.println("(input 0 untuk kembali)");
        System.out.println();

        cc.askSizeAndQty(choice, false);
        displayMainMenu();
    }

    public String askSize(String[] sizeList) {
        return checkString("size => ", sizeList, true);
    }

    public int askQty() {
        return checkInt("qty => ", true);
    }

    public void displayTotalMenu() {
        OrderDetailController oc = new OrderDetailController();
        NotesView nv = new NotesView();
        CustomerController cc = new CustomerController();

        System.out.println(formatBarrier("Konfirmasi & Pembayaran"));

        System.out.println(oc.displayTotalOrderList());

        nv.displayNotes();

        System.out.println("""
                1. Konfirmasi dan Bayar
                2. Kembali ke menu
                3. Ubah pesanan
                4. Kosongkan pesanan
                5. Tulis keterangan pesanan
                0. Keluar aplikasi
                """);

        while (true) {
            if (cc.totalMenuSelection(checkInt("=> "))) break;
        }
    }

    public void displayConfirmationMenu() {
        CustomerController cc = new CustomerController();

        System.out.println(formatBarrier("Mohon masukkan input pilihan Anda"));

        System.out.println("(Y) untuk lanjut");
        System.out.println("(N) untuk keluar");
        cc.confirmationMenuSelection(checkString("=> ", new String[]{"Y", "N"}, true));
    }

    public void displayNotesMenu() {
        NotesController nc = new NotesController();
        Scanner in = new Scanner(System.in);

        System.out.println(formatBarrier("Keterangan Pesanan"));

        System.out.println("Catatan: (ketik '0' pada baris terakhir\nuntuk mengakhiri catatan)");

        StringBuilder newNotes = new StringBuilder();
        while (true) {
            String newLine = in.nextLine();
            if (newLine.equals("0")) {
                break;
            } else {
                newNotes.append(newLine).append("\n");
            }
        }

        nc.createNotes(newNotes.toString());
    }

    public void displayEditMenu() {
        CustomerController cc = new CustomerController();
        OrderDetailController oc = new OrderDetailController();

        System.out.println(formatBarrier("Ubah Pesanan"));

        System.out.println(oc.displayTotalOrderList());

        System.out.println("""
        1. Ubah detail pesanan
        2. Menghapus pesanan
        0. Kembali
        """);

        while (true) {
            if (cc.editMenuSelection(checkInt("=> "))) break;
        }
    }

    public void displayReceiptMenu() {
        OrderDetailController oc = new OrderDetailController();
        System.out.println(oc.displayReceipt());
    }

    public void displayExitMenu() {
        System.out.println(formatBarrier("Terima kasih sudah menggunakan aplikasi ini"));
    }
}
