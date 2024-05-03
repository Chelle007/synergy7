package src.main.java.controller;

import src.main.java.model.entity.*;
import src.main.java.service.*;
import src.main.java.view.BasicView;
import src.main.java.view.CustomerView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static src.main.java.util.ColorUtils.COLOR_OF_ERROR;
import static src.main.java.view.ValidationView.checkInt;
import static src.main.java.view.ValidationView.checkString;

public class CustomerController {
    public void displayMainMenu(User user) {
        CustomerView cv = new CustomerView();
        BasicView bv = new BasicView();
        UserController uc = new UserController();

        cv.displayMainMenu();

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            switch(choice) {
                case 1:
                    displayRestaurantsMenu(user);
                    break;
                case 2:
                    displayOrderHistoryMenu(user);
                    break;
                case 3:
                    uc.displayProfileMenu(user);
                    break;
                case 4:
                    uc.displayLoginMenu();
                    break;
                case 0:
                    bv.displayExitMenu();
                    break;
                default:
                    bv.printChoiceInvalid();
                    validChoice = false;
                    break;
            }
        } while (!validChoice);
    }

    public void displayRestaurantsMenu(User user) {
        CustomerView cv = new CustomerView();
        RestaurantService rs = new RestaurantServiceImpl();
        BasicView bv = new BasicView();

        cv.displayRestaurantsMenu();

        while (true) {
            int choice = checkInt("=> ");
            if (choice == 0) {
                displayMainMenu(user);
                break;
            } else if (choice >= 1 && choice <= rs.getList().size()) {
                Restaurant restaurant = rs.getByChoice(choice);
                Order order = new Order(restaurant, user);
                displayMenuItemsMenu(user, restaurant, order);
                break;
            } else {
                bv.printChoiceInvalid();
            }
        }
    }

    public void displayMenuItemsMenu(User user, Restaurant restaurant, Order order) {
        CustomerView cv = new CustomerView();
        BasicView bv = new BasicView();

        cv.displayMenuItemsMenu(restaurant);

        while (true) {
            int choice = checkInt("=> ");
            if (choice == 0) {
                bv.displayExitMenu();
                break;
            } else if (choice >= 1 && choice <= restaurant.getMenuItemList().size()) {
                cv.displayQtyMenu(restaurant, choice);
                askSizeAndQty(order, restaurant, choice, false);
                displayMenuItemsMenu(user, restaurant, order);
                break;
            } else if (choice == 99) {
                if (order.getOrderDetailList().isEmpty()) {
                    bv.printlnColor("Anda belum memesan apa pun.", COLOR_OF_ERROR);
                } else {
                    displayTotalMenu(user, restaurant, order);
                    break;
                }
            } else if (choice == 100) {
                displayRestaurantsMenu(user);
                break;
            }
            else {
                bv.printChoiceInvalid();
            }
        }
    }

    public void askSizeAndQty(Order order, Restaurant restaurant, int choice, boolean update) {
        CustomerView cv = new CustomerView();
        OrderDetailService ods = new OrderDetailServiceImpl();
        MenuItemService mis = new MenuItemServiceImpl();

        String size = cv.askSize(new ArrayList<>(List.of("0", "S", "M", "L")));

        if (!size.equals("0")) {
            int qty = cv.askQty();
            if (qty != 0) {
                if (update) ods.update(order, choice, size, qty);
                else {
                    MenuItem menuItem = mis.getByRestaurantAndChoice(restaurant, choice);
                    OrderDetail orderDetail = new OrderDetail(size, qty, menuItem, order);
                    ods.create(orderDetail);
                }
            }
        }
    }

    public void displayTotalMenu(User user, Restaurant restaurant, Order order) {
        CustomerView cv = new CustomerView();
        BasicView bv = new BasicView();
        OrderDetailService ods = new OrderDetailServiceImpl();
        OrderService os = new OrderServiceImpl();

        cv.displayTotalMenu(order);

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            switch (choice) {
                case 1:
                    displayConfirmationMenu(user, restaurant, order);
                    break;
                case 2:
                    displayMenuItemsMenu(user, restaurant, order);
                    break;
                case 3:
                    displayEditMenu(user, restaurant, order);
                    break;
                case 4:
                    ods.clearList(order);
                    os.clearNotes(order);
                    displayMenuItemsMenu(user, restaurant, order);
                    break;
                case 5:
                    displayNotesMenu(order);
                    displayTotalMenu(user, restaurant, order);
                    break;
                case 0:
                    bv.displayExitMenu();
                    break;
                default:
                    bv.printChoiceInvalid();
                    validChoice = false;
                    break;
            }
        } while (!validChoice);
    }

    public void displayConfirmationMenu(User user, Restaurant restaurant, Order order) {
        CustomerView cv = new CustomerView();

        cv.displayConfirmationMenu();

        String choice = checkString("=> ", new ArrayList<>(List.of("Y", "N")), true);

        if (choice.equals("Y") || choice.equals("y")) {
            displayReceiptMenu(user, order);
        } else {
            displayTotalMenu(user, restaurant, order);
        }
    }

    public void displayReceiptMenu(User user, Order order) {
        CustomerView cv = new CustomerView();
        OrderService os = new OrderServiceImpl();

        order.setOrderTime(LocalDateTime.now());
        order.setCompleted(true);
        os.add(order);

        cv.displayReceiptMenu(order);

        displayMainMenu(user);
    }

    public void displayEditMenu(User user, Restaurant restaurant, Order order) {
        CustomerView cv = new CustomerView();
        OrderDetailController odc = new OrderDetailController();
        OrderDetailService ods = new OrderDetailServiceImpl();
        BasicView bv = new BasicView();

        cv.displayEditMenu(order);

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            switch (choice) {
                case 1:
                    askSizeAndQty(order, restaurant, odc.askOrderChoice(order), true);
                    displayEditMenu(user, restaurant, order);
                    break;
                case 2:
                    ods.deleteByChoice(order, odc.askOrderChoice(order));
                    if (order.getOrderDetailList().isEmpty()) displayMenuItemsMenu(user, restaurant, order);
                    else displayEditMenu(user, restaurant, order);
                    break;
                case 0:
                    displayTotalMenu(user, restaurant, order);
                    break;
                default:
                    bv.printChoiceInvalid();
                    validChoice = false;
                    break;
            }
        } while (!validChoice);
    }

    public void displayNotesMenu(Order order) {
        CustomerView cv = new CustomerView();
        OrderService os = new OrderServiceImpl();
        Scanner in = new Scanner(System.in);

        cv.displayNotesMenu();

        StringBuilder newNotes = new StringBuilder();
        while (true) {
            String newLine = in.nextLine();
            if (newLine.equals("0")) {
                break;
            } else {
                newNotes.append(newLine).append("\n");
            }
        }

        os.createNotes(order, newNotes.toString());
    }

    public void displayOrderHistoryMenu(User user) {
        CustomerView cv = new CustomerView();
        BasicView bv = new BasicView();

        cv.displayOrderHistoryMenu(user);

        while (true) {
            int choice = checkInt("=> ");
            if (choice == 0) {
                displayMainMenu(user);
                break;
            } else if (choice > 0 && choice <= user.getOrderList().size()) {
                cv.displayOrderHistoryDetails(user, choice);
                displayOrderHistoryMenu(user);
                break;
            } else {
                bv.printChoiceInvalid();
            }
        }
    }
}
