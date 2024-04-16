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

    public String askUsername() {
        new BasicView().printlnColor("Silakan masukkan username dan password", "bold");
        System.out.print("Username: ");
        return in.nextLine();
    }

    public String askPassword() {
        System.out.print("Password: ");
        return in.nextLine();
    }

    public void displayWrongUserPass() {
        new BasicView().printlnColor("Username/password Anda salah.\n", COLOR_OF_ERROR);
    }

    public void displayChangeProfileMenu(User user) {

    }
}
