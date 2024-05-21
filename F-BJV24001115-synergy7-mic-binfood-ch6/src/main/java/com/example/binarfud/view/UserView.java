//package com.example.binarfud.view;
//
//import com.example.binarfud.model.entity.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import static com.example.binarfud.util.ColorUtils.*;
//import static com.example.binarfud.util.AdditionalUtils.*;
//
//import java.util.Scanner;
//
//@Component
//public class UserView {
//    @Autowired BasicView basicView;
//
//    Scanner in = new Scanner(System.in);
//
//    public void displayWelcomeMenu() {
//        System.out.println(formatBarrier("Halaman Login/Signup Akun"));
//        System.out.println("1. Log in");
//        System.out.println("2. Sign up");
//        System.out.println("0. Keluar aplikasi");
//    }
//
//    public void printAskUserPass() {
//        basicView.printlnColor("\nSilakan masukkan username dan password", "bold");
//        basicView.printZeroOption();
//    }
//
//    public void printAskDetails() {
//        basicView.printlnColor("\nSilakan masukkan detail akun", "bold");
//        basicView.printZeroOption();
//    }
//
//    public String askUsername() {
//        System.out.print("Username: ");
//        return in.nextLine();
//    }
//
//    public String askPassword() {
//        System.out.print("Password: ");
//        return in.nextLine();
//    }
//
//    public String askEmail() {
//        System.out.print("Email: ");
//        return in.nextLine();
//    }
//
//    public String askRole() {
//        System.out.print("Role(CUSTOMER/SELLER): ");
//        return in.nextLine();
//    }
//
//    public void displayUsernameExisted() {
//        System.out.println(formatColor("Username telah diambil.", COLOR_OF_ERROR));
//    }
//
//    public void displayEmailExisted() {
//        System.out.println(formatColor("Email tersebut sudah memiliki akun.", COLOR_OF_ERROR));
//    }
//
//    public void displayPasswordInvalid() {
//        System.out.println(formatColor("Password invalid: panjang password harus lebih dari 8 huruf.", COLOR_OF_ERROR));
//    }
//
//    public void displayRoleInvalid() {
//        System.out.println(formatColor("Role invalid.", COLOR_OF_ERROR));
//    }
//
//    public void displayWrongUserPass() {
//        new BasicView().printlnColor("Username/password Anda salah.\n", COLOR_OF_ERROR);
//    }
//
//    public void displayProfileMenu(User user) {
//        System.out.println(formatBarrier("Profile"));
//        System.out.println("Username  : " + user.getUsername());
//        System.out.println("Email     : " + user.getEmail());
//        System.out.println("Role      : " + user.getRole().toString());
//        System.out.println("""
//
//                1. Ganti username
//                2. Ganti email
//                3. Ganti password
//                4. Hapus akun
//                0. Kembali ke halaman utama
//                """);
//    }
//}
