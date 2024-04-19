package src;

import src.main.java.view.CustomerView;
import static src.Data.*;

public class Main {
    public static void main(String[] args) {
        initializeMenu();
        initializePromo();
        CustomerView cv = new CustomerView();
        cv.displayMainMenu();
    }
}
