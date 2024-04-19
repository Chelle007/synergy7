package src.main.java.controller;

import src.main.java.model.entity.Order;
import src.main.java.service.*;
import src.main.java.view.CustomerView;
import src.main.java.view.OrderView;
import static src.main.java.util.AdditionalUtils.printChoiceInvalid;
import static src.main.java.util.ColorUtils.COLOR_OF_ERROR;
import static src.main.java.util.ColorUtils.printlnColor;

import java.util.ArrayList;
import java.util.Set;


public class CustomerController {
    public boolean mainMenuSelection(int choice) {
        CustomerView cv = new CustomerView();
        MenuItemService mis = new MenuItemServiceImpl();
        OrderService os = new OrderServiceImpl();

        if (choice == 0) {
            cv.displayExitMenu();
            return true;
        } else if (choice >= 1 && choice <= mis.getList().size()) {
            cv.displayQtyMenu(choice);
            return true;
        } else if (choice == 99) {
            if (os.getList().isEmpty()) {
                printlnColor("Anda belum memesan apa pun.", COLOR_OF_ERROR);
            } else {
                cv.displayTotalMenu();
                return true;
            }
        } else {
            printChoiceInvalid();
        }
        return false;
    }

    public void askSizeAndQty(int choice, boolean update) {
        CustomerView cv = new CustomerView();
        MenuItemService mis = new MenuItemServiceImpl();
        OrderService os = new OrderServiceImpl();

        Set<String> availableSize = mis.getAvailableSize(choice-1);
        ArrayList<String> sizeList = new ArrayList<>(availableSize);
        sizeList.add("0");
        String size = cv.askSize(sizeList);

        if (!size.equals("0")) {
            int qty = cv.askQty();
            if (qty != 0) {
                if (update) os.update(choice-1, size, qty);
                else {
                    Order order = new Order(mis.get(choice-1), size, qty);
                    os.create(order);
                }
            }
        }
    }

    public boolean totalMenuSelection(int choice) {
        CustomerView cv = new CustomerView();
        OrderService os = new OrderServiceImpl();
        NotesService ns = new NotesServiceImpl();

        switch (choice) {
            case 1:
                cv.displayConfirmationMenu();
                return true;
            case 2:
                cv.displayMainMenu();
                return true;
            case 3:
                cv.displayEditMenu();
                return true;
            case 4:
                os.clearList();
                ns.clear();
                cv.displayMainMenu();
                return true;
            case 5:
                cv.displayNotesMenu();
                cv.displayTotalMenu();
                return true;
            case 0:
                cv.displayExitMenu();
                return true;
            default:
                printChoiceInvalid();
                break;
        }

        return false;
    }

    public void confirmationMenuSelection(String choice) {
        CustomerView cv = new CustomerView();

        if (choice.equals("Y") || choice.equals("y")) {
            cv.displayReceiptMenu();
        } else {
            cv.displayTotalMenu();
        }
    }

    public boolean editMenuSelection(int choice) {
        CustomerView cv = new CustomerView();
        OrderView ov = new OrderView();
        OrderService os = new OrderServiceImpl();
        PromoService ps = new PromoServiceImpl();

        switch (choice) {
            case 1:
                askSizeAndQty(ov.askOrderChoice(), true);
                cv.displayEditMenu();
                return true;
            case 2:
                os.delete(ov.askOrderChoice()-1);
                ps.resetPromo();
                if (os.isEmpty()) cv.displayMainMenu();
                else cv.displayEditMenu();
                return true;
            case 0:
                cv.displayTotalMenu();
                return true;
            default:
                printChoiceInvalid();
                break;
        }

        return false;
    }
}
