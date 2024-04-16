package src.controller;

import src.model.entity.User;
import src.service.UserService;
import src.service.UserServiceImpl;
import src.view.UserView;

public class UserController {
    public void displayLoginMenu() {
        UserService us = new UserServiceImpl();
        UserView uv = new UserView();

        uv.displayLoginMenu();
        while (true) {
            String username = uv.askUsername();
            String password = uv.askPassword();
            User user = us.getByUserPass(username, password);
            if (user == null) {
                uv.displayWrongUserPass();
            } else {
                displayMainMenu(user);
                break;
            }
        }
    }

    public void displayMainMenu(User user) {
        CustomerController cc = new CustomerController();
        SellerController sc = new SellerController();

        if (user.getRole()== User.Role.CUSTOMER) {
            cc.displayMainMenu(user);
        } else {
            sc.displayMainMenu(user.getRestaurant());
        }
    }
}
