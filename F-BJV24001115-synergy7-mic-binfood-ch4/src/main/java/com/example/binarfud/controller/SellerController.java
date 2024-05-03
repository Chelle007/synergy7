package com.example.binarfud.controller;

import com.example.binarfud.model.entity.*;
import com.example.binarfud.service.*;
import com.example.binarfud.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static com.example.binarfud.view.ValidationView.checkInt;

@Controller
public class SellerController {
    @Autowired BasicView basicView;
    @Autowired RestaurantService restaurantService;
    @Autowired MenuItemService menuItemService;
    @Autowired SellerView sellerView;
    @Autowired UserController userController;
    @Autowired MenuItemController menuItemController;
    @Autowired RestaurantController restaurantController;

    public void displayMainMenu(User user) {
        sellerView.displayMainMenu();

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
                    userController.displayProfileMenu(user);
                    break;
                case 4:
                    userController.displayLoginMenu();
                    break;
                case 0:
                    basicView.displayExitMenu();
                    break;
                default:
                    basicView.printChoiceInvalid();
                    validChoice = false;
                    break;
            }
        } while (!validChoice);
    }

    public void displayAddRestaurantMenu(User user) {
        sellerView.displayAddRestaurantMenu();

        while (true) {
            String name;
            while (true) {
                name = sellerView.askName();
                if (name.equals("0")) break;
                else if (restaurantService.restaurantNameExisted(name)) {
                    sellerView.printNameExisted();
                } else {
                    break;
                }
            }
            if (name.equals("0")) break;

            String location = sellerView.askLocation();
            if (location.equals("0")) break;

            restaurantController.createRestaurant(name, location, user);
            break;
        }

        displayMainMenu(user);
    }

    public void displayRestaurantsMenu(User user) {
        sellerView.displayRestaurantsMenu(user);

        while (true) {
            int choice = checkInt("=> ");
            if (choice == 0) {
                displayMainMenu(user);
                break;
            } else if (choice >= 1 && choice <= restaurantService.getByUser(user).size()) {
                Restaurant restaurant = restaurantService.getByUserAndChoice(user, choice);
                displayEditRestaurantMenu(user, restaurant);
                break;
            } else {
                basicView.printChoiceInvalid();
            }
        }
    }

    public void displayEditRestaurantMenu(User user, Restaurant restaurant) {
        sellerView.displayEditRestaurantMenu(restaurant, menuItemService.getByRestaurant(restaurant));

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            System.out.println();
            switch(choice) {
                case 1:
                    basicView.printZeroOption();
                    askAddMenuItemMenu(restaurant);
                    displayEditRestaurantMenu(user, restaurant);
                    break;
                case 2:
                    int choice2 = askSelectedMenuItem(restaurant);
                    if (choice2 == 0) {
                        displayEditRestaurantMenu(user, restaurant);
                    } else {
                        MenuItem menuItem = menuItemService.getByRestaurantAndChoice(restaurant, choice2);
                        displayEditMenuItemMenu(user, restaurant, menuItem);
                    }
                    break;
                case 3:
                    int choice3 = askSelectedMenuItem(restaurant);
                    if (choice3 == 0) {
                        displayEditRestaurantMenu(user, restaurant);
                    } else {
                        menuItemService.safeDelete(menuItemService.getByRestaurantAndChoice(restaurant, choice3));
                        displayEditRestaurantMenu(user, restaurant);
                    }
                    break;
                case 4:
                    displayEditRestaurantDetailMenu(user, restaurant);
                    break;
                case 5:
                    displayRestaurantsMenu(user);
                    break;
                default:
                    basicView.printChoiceInvalid();
                    validChoice = false;
                    break;
            }
        } while (!validChoice);
    }

    public void askAddMenuItemMenu(Restaurant restaurant) {
        while (true) {
            String name = askNewMenuItemName(restaurant);
            if (name.equals("0")) break;

            String type = askNewMenuItemType();
            if (type.equals("0")) break;

            int priceM = sellerView.askPriceM();
            if (priceM == 0) break;

            Integer priceS = null;
            if (sellerView.askWantInsertPriceS()) {
                priceS = sellerView.askPriceS();
                if (priceS == 0) break;
            }

            Integer priceL = null;
            if (sellerView.askWantInsertPriceL()) {
                priceL = sellerView.askPriceL();
                if (priceL == 0) break;
            }

            menuItemController.createMenuItem(name, type, priceS, priceM, priceL, restaurant);
            break;
        }
    }

    public int askSelectedMenuItem(Restaurant restaurant) {
        int choice;
        while (true) {
            basicView.printZeroOption();
            choice = checkInt("menu item: ");
            if (choice >= 0 && choice <= menuItemService.getByRestaurant(restaurant).size()) {
                break;
            } else {
                basicView.printChoiceInvalid();
            }
        }
        return choice;
    }

    public void displayEditMenuItemMenu(User user, Restaurant restaurant, MenuItem menuItem) {
        sellerView.displayEditMenuItemMenu(menuItem);

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            switch(choice) {
                case 1:
                    String name = askNewMenuItemName(restaurant);
                    if (name.equals("0")) break;
                    else menuItemService.updateName(menuItem, name);
                    displayEditMenuItemMenu(user, restaurant, menuItem);
                    break;
                case 2:
                    String type = askNewMenuItemType();
                    if (type.equals("0")) break;
                    else menuItemService.updateType(menuItem, menuItemService.convertStringToType(type));
                    displayEditMenuItemMenu(user, restaurant, menuItem);
                    break;
                case 3:
                    int priceS = askNewMenuItemPrice("S");
                    menuItemService.updatePriceS(menuItem, priceS);
                    displayEditMenuItemMenu(user, restaurant, menuItem);
                    break;
                case 4:
                    int priceM = askNewMenuItemPrice("M");
                    if (priceM != 0) menuItemService.updatePriceM(menuItem, priceM);
                    displayEditMenuItemMenu(user, restaurant, menuItem);
                    break;
                case 5:
                    int priceL = askNewMenuItemPrice("L");
                    menuItemService.updatePriceL(menuItem, priceL);
                    displayEditMenuItemMenu(user, restaurant, menuItem);
                    break;
                case 6:
                    menuItemService.safeDelete(menuItem);
                    displayEditRestaurantMenu(user, restaurant);
                    break;
                case 0:
                    displayEditRestaurantMenu(user, restaurant);
                    break;
                default:
                    basicView.printChoiceInvalid();
                    validChoice = false;
                    break;
            }
        } while (!validChoice);
    }

    public String askNewMenuItemName(Restaurant restaurant) {
        String name;
        while(true) {
            name = sellerView.askName();
            if (menuItemService.menuItemNameExistedInRestaurant(restaurant, name)) {
                sellerView.printNameExisted();
            } else {
                break;
            }
        }
        return name;
    }

    public String askNewMenuItemType() {
        String foodType;
        while (true) {
            String availableFoodType = menuItemService.getAvailableFoodTypeChoice();
            foodType = sellerView.askFoodType(availableFoodType);
            if (foodType.equals("0")) break;
            else if (menuItemService.invalidFoodType(foodType)) {
                sellerView.printInvalidFoodType();
            } else {
                break;
            }
        }
        return foodType;
    }

    public int askNewMenuItemPrice(String size) {
        if (size.equals("M")) basicView.printZeroOption();
        else sellerView.printZeroMeansDeleteSize();

        return checkInt("=> ");
    }

    public void displayEditRestaurantDetailMenu(User user, Restaurant restaurant) {
        sellerView.displayEditRestaurantDetailMenu(restaurant);

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            switch(choice) {
                case 1:
                    askNewRestaurantName(restaurant);
                    displayEditRestaurantDetailMenu(user, restaurant);
                    break;
                case 2:
                    askNewRestaurantLocation(restaurant);
                    displayEditRestaurantDetailMenu(user, restaurant);
                    break;
                case 3:
                    restaurantService.changeStatus(restaurant);
                    displayEditRestaurantDetailMenu(user, restaurant);
                    break;
                case 4:
                    restaurantService.safeDelete(restaurant);
                    displayRestaurantsMenu(user);
                    break;
                case 0:
                    displayEditRestaurantMenu(user, restaurant);
                    break;
                default:
                    basicView.printChoiceInvalid();
                    validChoice = false;
                    break;
            }
        } while (!validChoice);
    }

    public void askNewRestaurantName(Restaurant restaurant) {
        while(true) {
            String name = sellerView.askName();
            if (restaurantService.restaurantNameExisted(name)) {
                sellerView.printNameExisted();
            } else {
                restaurantService.updateName(restaurant, name);
                break;
            }
        }
    }

    public void askNewRestaurantLocation(Restaurant restaurant) {
        String location = sellerView.askLocation();
        restaurantService.updateLocation(restaurant, location);
    }
}
