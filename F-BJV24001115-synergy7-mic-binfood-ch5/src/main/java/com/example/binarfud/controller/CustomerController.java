//package com.example.binarfud.controller;
//
//import com.example.binarfud.model.entity.*;
//import com.example.binarfud.service.*;
//import com.example.binarfud.view.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StopWatch;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import static com.example.binarfud.util.ColorUtils.COLOR_OF_ERROR;
//import static com.example.binarfud.view.ValidationView.checkInt;
//import static com.example.binarfud.view.ValidationView.checkString;
//
//@Controller
//@Slf4j
//public class CustomerController {
//    @Autowired CustomerView customerView;
//    @Autowired BasicView basicView;
//    @Autowired UserController userController;
//    @Autowired RestaurantService restaurantService;
//    @Autowired OrderDetailService orderDetailService;
//    @Autowired MenuItemService menuItemService;
//    @Autowired OrderService orderService;
//    @Autowired OrderDetailController orderDetailController;
//
//    public void displayMainMenu(User user) {
//        customerView.displayMainMenu();
//
//        boolean validChoice;
//        do {
//            validChoice = true;
//            int choice = checkInt("=> ");
//            switch(choice) {
//                case 1:
//                    displayRestaurantsMenu(user);
//                    break;
//                case 2:
//                    displayOrderHistoryMenu(user);
//                    break;
//                case 3:
//                    userController.displayProfileMenu(user);
//                    break;
//                case 4:
//                    userController.displayWelcomeMenu();
//                    break;
//                case 0:
//                    basicView.displayExitMenu();
//                    break;
//                default:
//                    basicView.printChoiceInvalid();
//                    validChoice = false;
//                    break;
//            }
//        } while (!validChoice);
//    }
//
//    public void displayRestaurantsMenu(User user) {
//        customerView.displayOpenRestaurantsMenu();
//
//        while (true) {
//            int choice = checkInt("=> ");
//            if (choice == 0) {
//                displayMainMenu(user);
//                break;
//            } else if (choice >= 1 && choice <= restaurantService.getOpenList().size()) {
//                Restaurant restaurant = restaurantService.getByOpenChoice(choice);
//                Order order = Order.builder()
//                        .restaurant(restaurant)
//                        .user(user)
//                        .completed(false)
//                        .build();
//                orderService.create(order);
//                displayMenuItemsMenu(user, restaurant, order, 1);
//                break;
//            } else {
//                basicView.printChoiceInvalid();
//            }
//        }
//    }
//
//    public void displayMenuItemsMenu(User user, Restaurant restaurant, Order order, int page) {
//        customerView.displayMenuItemsMenu(restaurant, page);
//
//        while (true) {
//            int choice = checkInt("=> ");
//            if (choice == 0) {
//                basicView.displayExitMenu();
//                break;
//            } else if (choice >= 1 && choice <= menuItemService.getByRestaurant(restaurant).size()) {
//                customerView.displayQtyMenu(restaurant, choice);
//                askSizeAndQty(order, restaurant, choice, false);
//                displayMenuItemsMenu(user, restaurant, order, 1);
//                break;
//            } else if (choice == 99) {
//                if (orderDetailService.getByOrder(order).isEmpty()) {
//                    basicView.printlnColor("Anda belum memesan apa pun.", COLOR_OF_ERROR);
//                } else {
//                    displayTotalMenu(user, restaurant, order);
//                    break;
//                }
//            } else if (choice == 100) {
//                displayRestaurantsMenu(user);
//                break;
//            } else if (choice == 101) {
//                page--;
//                int totalPage = menuItemService.getTotalPage(restaurant);
//                displayMenuItemsMenu(user, restaurant, order, (page<=0) ? page+totalPage : page);
//                break;
//            } else if (choice == 102) {
//                page++;
//                int totalPage = menuItemService.getTotalPage(restaurant);
//                displayMenuItemsMenu(user, restaurant, order, (page>totalPage) ? page-totalPage : page);
//                break;
//            }
//            else {
//                basicView.printChoiceInvalid();
//            }
//        }
//    }
//
//    public void askSizeAndQty(Order order, Restaurant restaurant, int choice, boolean update) {
//        MenuItem menuItem = menuItemService.getByRestaurantAndChoice(restaurant, choice);
//        List<String> availableSize = menuItemService.getAvailableSize(menuItem);
//        availableSize.add("0");
//        String size = customerView.askSize(availableSize);
//
//        if (!size.equals("0")) {
//            int qty = customerView.askQty();
//            if (qty != 0) {
//                if (update) orderDetailService.update(order, choice, size, qty);
//                else {
//                    OrderDetail orderDetail = OrderDetail.builder()
//                            .size(size)
//                            .qty(qty)
//                            .menuItem(menuItem)
//                            .order(order)
//                            .build();
//                    orderDetailService.create(orderDetail);
//                }
//            }
//        }
//    }
//
//    public void displayTotalMenu(User user, Restaurant restaurant, Order order) {
//        StopWatch sw = new StopWatch();
//        sw.start();
//        customerView.displayTotalMenu(order);
//        sw.stop();
//
//        if(sw.getTotalTimeSeconds()>1){
//            log.warn("Method running selama >1 detik: {}", sw.getTotalTimeMillis());
//        }
//
//        boolean validChoice;
//        do {
//            validChoice = true;
//            int choice = checkInt("=> ");
//            switch (choice) {
//                case 1:
//                    displayConfirmationMenu(user, restaurant, order);
//                    break;
//                case 2:
//                    displayMenuItemsMenu(user, restaurant, order, 1);
//                    break;
//                case 3:
//                    displayEditMenu(user, restaurant, order);
//                    break;
//                case 4:
//                    orderDetailService.safeDeleteAllOrderDetailsByOrder(order);
//                    orderService.clearNotes(order);
//                    displayMenuItemsMenu(user, restaurant, order, 1);
//                    break;
//                case 5:
//                    displayNotesMenu(order);
//                    displayTotalMenu(user, restaurant, order);
//                    break;
//                case 0:
//                    basicView.displayExitMenu();
//                    break;
//                default:
//                    basicView.printChoiceInvalid();
//                    validChoice = false;
//                    break;
//            }
//        } while (!validChoice);
//    }
//
//    public void displayConfirmationMenu(User user, Restaurant restaurant, Order order) {
//        customerView.displayConfirmationMenu();
//
//        String choice = checkString("=> ", new ArrayList<>(List.of("Y", "N")), true);
//
//        if (choice.equals("Y") || choice.equals("y")) {
//            displayReceiptMenu(user, order);
//        } else {
//            displayTotalMenu(user, restaurant, order);
//        }
//    }
//
//    public void displayReceiptMenu(User user, Order order) {
//        orderService.completeOrder(order);
//
//        customerView.displayReceiptMenu(order);
//
//        displayMainMenu(user);
//    }
//
//    public void displayEditMenu(User user, Restaurant restaurant, Order order) {
//        customerView.displayEditMenu(order);
//
//        boolean validChoice;
//        do {
//            validChoice = true;
//            int choice = checkInt("=> ");
//            switch (choice) {
//                case 1:
//                    askSizeAndQty(order, restaurant, orderDetailController.askOrderChoice(order), true);
//                    displayEditMenu(user, restaurant, order);
//                    break;
//                case 2:
//                    orderDetailService.safeDeleteByOrderAndChoice(order, orderDetailController.askOrderChoice(order));
//                    if (orderDetailService.getByOrder(order).isEmpty()) displayMenuItemsMenu(user, restaurant, order, 1);
//                    else displayEditMenu(user, restaurant, order);
//                    break;
//                case 0:
//                    displayTotalMenu(user, restaurant, order);
//                    break;
//                default:
//                    basicView.printChoiceInvalid();
//                    validChoice = false;
//                    break;
//            }
//        } while (!validChoice);
//    }
//
//    public void displayNotesMenu(Order order) {
//        Scanner in = new Scanner(System.in);
//
//        customerView.displayNotesMenu();
//
//        StringBuilder newNotes = new StringBuilder();
//        while (true) {
//            String newLine = in.nextLine();
//            if (newLine.equals("0")) {
//                break;
//            } else {
//                newNotes.append(newLine).append("\n");
//            }
//        }
//
//        orderService.createNotes(order, newNotes.toString());
//    }
//
//    public void displayOrderHistoryMenu(User user) {
//        List<Order> orders = orderService.getByUser(user);
//
//        customerView.displayOrderHistoryMenu(orders);
//
//        while (true) {
//            int choice = checkInt("=> ");
//            if (choice == 0) {
//                displayMainMenu(user);
//                break;
//            } else if (choice > 0 && choice <= orderService.getByUser(user).size()) {
//                customerView.displayOrderHistoryDetails(orderService.getByUser(user), choice);
//                displayOrderHistoryMenu(user);
//                break;
//            } else {
//                basicView.printChoiceInvalid();
//            }
//        }
//    }
//}
