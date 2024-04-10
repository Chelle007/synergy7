package src.view;

import src.controller.ProfileController;
import static src.util.AdditionalUtils.*;

import java.util.Scanner;

public class ProfileView {
    Scanner in = new Scanner(System.in);

    public void displayLoginMenu() {
        ProfileController prc = new ProfileController();
        System.out.println(formatBarrier("Halaman Login Akun"));
        System.out.println("Silakan masukkan username dan password");
        System.out.print("Username: ");
        String username = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine();
        prc.LoginAccount(username, password);
    }
}
