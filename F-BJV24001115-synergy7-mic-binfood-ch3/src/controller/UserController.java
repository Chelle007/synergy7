package src.controller;

import src.model.entity.User;
import src.service.UserService;
import src.service.UserServiceImpl;
import src.view.BasicView;
import src.view.UserView;

import static src.util.ValidationUtils.checkInt;

public class UserController {
    public void displayLoginMenu() {
        UserService us = new UserServiceImpl();
        UserView uv = new UserView();

        uv.displayLoginMenu();
        while (true) {
            uv.welcome();
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
//        SellerController sc = new SellerController();

        if (user.getRole()== User.Role.CUSTOMER) {
            cc.displayMainMenu(user);
        }
//        else {
//            sc.displayMainMenu(user.getRestaurant());
//        }
    }

    public void displayProfileMenu(User user) {
        UserView uv = new UserView();
        CustomerController cc = new CustomerController();
        BasicView bv = new BasicView();

        uv.displayProfileMenu(user);

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            switch(choice) {
                case 1:
                    askNewUsername(user);
                    displayProfileMenu(user);
                    break;
                case 2:
                    askNewEmail(user);
                    displayProfileMenu(user);
                    break;
                case 3:
                    askNewPassword(user);
                    displayProfileMenu(user);
                    break;
                case 0:
                    cc.displayMainMenu(user);
                    break;
                default:
                    bv.printChoiceInvalid();
                    validChoice = false;
                    break;
            }
        } while (!validChoice);
    }

    public void askNewUsername(User user) {
        UserView uv = new UserView();
        UserService us = new UserServiceImpl();

        while(true) {
            String username = uv.askUsername();
            if (us.usernameExists(username)) {
                uv.displayUsernameExisted();
            } else {
                user.setUsername(username);
                break;
            }
        }
    }

    public void askNewEmail(User user) {
        UserView uv = new UserView();
        UserService us = new UserServiceImpl();

        while(true) {
            String email = uv.askEmail();
            if (us.emailExists(email)) {
                uv.displayEmailExisted();
            } else {
                user.setEmail(email);
                break;
            }
        }
    }

    public void askNewPassword(User user) {
        UserView uv = new UserView();
        UserService us = new UserServiceImpl();

        while(true) {
            String password = uv.askPassword();
            if (us.passwordInvalid(password)) {
                uv.displayPasswordInvalid();
            } else {
                user.setPassword(password);
                break;
            }
        }
    }
}
