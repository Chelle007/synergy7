package src.controller;

import src.model.entity.OrderDetail;
import src.service.*;
import src.view.CustomerView;
import src.view.OrderView;
import static src.util.AdditionalUtils.printChoiceInvalid;
import static src.util.ColorUtils.COLOR_OF_ERROR;
import static src.util.ColorUtils.printlnColor;


public class CustomerController {
    public boolean mainMenuSelection(int choice) {
        CustomerView cv = new CustomerView();
        MenuItemService mis = new MenuItemServiceImpl();
        OrderDetailService ods = new OrderDetailServiceImpl();

        if (choice == 0) {
            cv.displayExitMenu();
            return true;
        } else if (choice >= 1 && choice <= mis.getList().size()) {
            cv.displayQtyMenu(choice);
            return true;
        } else if (choice == 99) {
            if (ods.getList().isEmpty()) {
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
        OrderDetailService ods = new OrderDetailServiceImpl();

        String size = cv.askSize(new String[]{"0", "S", "M", "L"});

        if (!size.equals("0")) {
            int qty = cv.askQty();
            if (qty != 0) {
                if (update) ods.update(choice-1, size, qty);
//                else {
//                    OrderDetail orderDetail = new OrderDetail(mis.get(choice-1), size, qty);
//                    ods.create(orderDetail);
//                }
            }
        }
    }

    public boolean totalMenuSelection(int choice) {
        CustomerView cv = new CustomerView();
        OrderDetailService ods = new OrderDetailServiceImpl();
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
                ods.clearList();
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
        OrderDetailService ods = new OrderDetailServiceImpl();
        PromoService ps = new PromoServiceImpl();

        switch (choice) {
            case 1:
                askSizeAndQty(ov.askOrderChoice(), true);
                cv.displayEditMenu();
                return true;
            case 2:
                ods.delete(ov.askOrderChoice()-1);
                ps.resetPromo();
                if (ods.isEmpty()) cv.displayMainMenu();
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
