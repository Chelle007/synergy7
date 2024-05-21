//package com.example.binarfud.view;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import static com.example.binarfud.util.ColorUtils.*;
//
//import java.util.List;
//import java.util.Scanner;
//import java.util.StringJoiner;
//
//@Component
//@Slf4j
//public class ValidationView {
//    static Scanner input = new Scanner(System.in);
//
//    private ValidationView() {
//
//    }
//
//    // Method untuk cek apakah input string sesuai dengan pilihan
//    public static String checkString(String question, List<String> choices, boolean caseInsensitive) {
//        while (true) {
//            System.out.print(question);
//            String answer = input.nextLine();
//            for (String choice : choices) {
//                if (answer.equals(choice) || (caseInsensitive && answer.equalsIgnoreCase(choice))) {
//                    return answer;
//                }
//            }
//            StringJoiner choiceList = new StringJoiner(" / ");
//            choices.forEach(choiceList::add);
//            new BasicView().printlnColor(String.format("Mohon masukkan input sesuai pilihan. ( %s )", choiceList), COLOR_OF_ERROR);
//        }
//    }
//
//    // Method untuk cek apakah input merupakan integer
//    public static int checkInt(String question, boolean mustPositive) {
//        while (true) {
//            System.out.print(question);
//            try {
//                int answer = input.nextInt();
//                input.nextLine();
//                if (mustPositive && answer < 0) {
//                    new BasicView().printlnColor("Eror. Mohon input angka positif.", COLOR_OF_ERROR);
//                    continue;
//                }
//                return answer;
//            }
//            catch (Exception e) {
//                new BasicView().printlnColor("Eror. Mohon input angka dengan benar.", COLOR_OF_ERROR);
//                input.reset();
//                input.nextLine();
//            }
//        }
//    }
//
//    public static int checkInt(String question) {
//        return checkInt(question, false);
//    }
//}
