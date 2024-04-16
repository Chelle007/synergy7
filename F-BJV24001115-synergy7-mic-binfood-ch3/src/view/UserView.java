package src.view;

import src.model.entity.User;

import static src.util.ColorUtils.*;
import static src.util.AdditionalUtils.*;

import java.util.Scanner;

public class UserView {
    Scanner in = new Scanner(System.in);

    public void displayLoginMenu() {
        System.out.println(formatBarrier("Halaman Login Akun"));
    }

    public void welcome() {
        new BasicView().printlnColor("Silakan masukkan username dan password", "bold");
    }

    public String askUsername() {
        System.out.print("Username: ");
        return in.nextLine();
    }

    public String askPassword() {
        System.out.print("Password: ");
        return in.nextLine();
    }

    public String askEmail() {
        System.out.print("Email: ");
        return in.nextLine();
    }

    public void displayUsernameExisted() {
        System.out.println(formatColor("Username telah diambil.", COLOR_OF_ERROR));
    }

    public void displayEmailExisted() {
        System.out.println(formatColor("Email tersebut sudah memiliki akun.", COLOR_OF_ERROR));
    }

    public void displayPasswordInvalid() {
        System.out.println(formatColor("Password invalid: panjang password harus lebih dari 8 huruf.", COLOR_OF_ERROR));
    }

    public void displayWrongUserPass() {
        new BasicView().printlnColor("Username/password Anda salah.\n", COLOR_OF_ERROR);
    }

    public void displayProfileMenu(User user) {
        System.out.println(formatBarrier("Profile"));
        System.out.println("Username  : " + user.getUsername());
        System.out.println("Email     : " + user.getEmail());
        System.out.println("Role      : " + user.getRole().toString());
        if (user.getRole() == User.Role.SELLER) {
            System.out.println("Restaurant: " + user.getRestaurant().getName());
        }
        System.out.println("""
                
                1. Ganti username
                2. Ganti email
                3. Ganti password
                0. Kembali ke halaman utama
                """);
    }
}
