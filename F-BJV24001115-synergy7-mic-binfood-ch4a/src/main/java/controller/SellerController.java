package src.main.java.controller;

import src.main.java.model.entity.Restaurant;
import src.main.java.model.entity.User;
import src.main.java.service.MenuItemService;
import src.main.java.service.MenuItemServiceImpl;
import src.main.java.service.RestaurantService;
import src.main.java.service.RestaurantServiceImpl;
import src.main.java.view.BasicView;
import src.main.java.view.SellerView;

import static src.main.java.view.ValidationView.checkInt;

public class SellerController {
    public void displayMainMenu(User user) {
        SellerView sv = new SellerView();
        BasicView bv = new BasicView();
        UserController uc = new UserController();

        sv.displayMainMenu();

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            switch(choice) {
                case 1:
                    displayAddRestaurantMenu(user);
                    break;
                case 2:
                    displayRestaurantsMenu(user);
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

    public void displayAddRestaurantMenu(User user) {

    }

    public void displayRestaurantsMenu(User user) {
        SellerView sv = new SellerView();
        RestaurantService rs = new RestaurantServiceImpl();
        BasicView bv = new BasicView();

        sv.displayRestaurantsMenu(user);

        while (true) {
            int choice = checkInt("=> ");
            if (choice == 0) {
                displayMainMenu(user);
                break;
            } else if (choice >= 1 && choice <= user.getRestaurantList().size()) {
                Restaurant restaurant = rs.getByChoice(choice);
                displayEditRestaurantMenu(user, restaurant);
                break;
            } else {
                bv.printChoiceInvalid();
            }
        }
    }

    public void displayEditRestaurantMenu(User user, Restaurant restaurant) {
        SellerView sv = new SellerView();
        BasicView bv = new BasicView();

        sv.displayEditRestaurantMenu(restaurant, restaurant.getMenuItemList());

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            System.out.println();
            switch(choice) {
                case 1:
                    bv.printZeroOption();
                    askAddMenuItemMenu(restaurant);
                    displayEditRestaurantMenu(user, restaurant);
                    break;
                case 2:
                    askEditMenuItemMenu(restaurant);
                    displayEditRestaurantMenu(user, restaurant);
                    break;
                case 3:
                    askDeleteMenuItemMenu(restaurant);
                    displayEditRestaurantMenu(user, restaurant);
                    break;
                case 4:
                    displayEditRestaurantDetailMenu(restaurant);
                    break;
                case 5:
                    displayRestaurantsMenu(user);
                    break;
                default:
                    bv.printChoiceInvalid();
                    validChoice = false;
                    break;
            }
        } while (!validChoice);
    }

    public void askAddMenuItemMenu(Restaurant restaurant) {
        SellerView sv = new SellerView();
        MenuItemService mis = new MenuItemServiceImpl();

        while (true) {
            String name;
            while (true) {
                name = sv.askMenuItemName();
                if (name.equals("0")) break;
                else if (mis.menuItemNameExistedInRestaurant(restaurant, name)) {
                    sv.printMenuItemNameExisted();
                } else {
                    break;
                }
            }
            if (name.equals("0")) break;

            String foodType;
            while (true) {
                String availableFoodType = mis.getAvailableFoodTypeChoice();
                foodType = sv.askFoodType(availableFoodType);
                if (foodType.equals("0")) break;
                else if (mis.invalidFoodType(foodType)) {
                    sv.printInvalidFoodType();
                } else {
                    break;
                }
            }
            if (foodType.equals("0")) break;

            int priceM = sv.askPriceM();
            if (priceM == 0) break;

            Integer priceS = null;
            if (sv.askWantInsertPriceS()) {
                priceS = sv.askPriceS();
                if (priceS == 0) break;
            }

            Integer priceL = null;
            if (sv.askWantInsertPriceL()) {
                priceL = sv.askPriceL();
                if (priceL == 0) break;
            }

            mis.create(name, foodType, priceS, priceM, priceL, restaurant);
            break;
        }
    }

    public void askEditMenuItemMenu(Restaurant restaurant) {

    }

    public void askDeleteMenuItemMenu(Restaurant restaurant) {

    }

    public void displayEditRestaurantDetailMenu(Restaurant restaurant) {

    }
}
