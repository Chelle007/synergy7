package com.example.binarfud.controller;

import com.example.binarfud.model.entity.*;
import com.example.binarfud.service.*;
import com.example.binarfud.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static com.example.binarfud.view.ValidationView.checkInt;

@Controller
public class UserController {
    @Autowired BasicView basicView;
    @Autowired UserService userService;
    @Autowired UserView userView;
    @Autowired CustomerController customerController;
    @Autowired SellerController sellerController;

    public void displayWelcomeMenu() {
        userView.displayWelcomeMenu();

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            switch(choice) {
                case 1:
                    displayLoginMenu();
                    break;
                case 2:
                    displaySignupMenu();
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

    public void displayLoginMenu() {
        while (true) {
            userView.printAskUserPass();

            String username = userView.askUsername();
            if (username.equals("0")) {
                displayWelcomeMenu();
                break;
            }

            String password = userView.askPassword();
            if (password.equals("0")) {
                displayWelcomeMenu();
                break;
            }

            User user = userService.getByUserPass(username, password);
            if (user == null) {
                userView.displayWrongUserPass();
            } else {
                displayMainMenu(user);
                break;
            }
        }
    }

    public void displaySignupMenu() {
        while (true) {
            userView.printAskDetails();

            String username = askNewUsername();
            if (username.equals("0")) {
                break;
            }

            String email = askNewEmail();
            if (email.equals("0")) {
                break;
            }

            String password = askNewPassword();
            if (password.equals("0")) {
                break;
            }

            String role = askRole();
            if (role.equals("0")) {
                break;
            }

            User user = User.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .role(userService.convertStringToRole(role))
                    .build();
            userService.create(user);
            break;
        }
        displayWelcomeMenu();
    }

    public void displayMainMenu(User user) {
        if (user.getRole()== User.Role.CUSTOMER) {
            customerController.displayMainMenu(user);
        } else {
            sellerController.displayMainMenu(user);
        }
    }

    public void displayProfileMenu(User user) {
        userView.displayProfileMenu(user);

        boolean validChoice;
        do {
            validChoice = true;
            int choice = checkInt("=> ");
            switch(choice) {
                case 1:
                    userService.updateUsername(user, askNewUsername());
                    displayProfileMenu(user);
                    break;
                case 2:
                    userService.updateEmail(user, askNewEmail());
                    displayProfileMenu(user);
                    break;
                case 3:
                    userService.updatePassword(user, askNewPassword());
                    displayProfileMenu(user);
                    break;
                case 4:
                    userService.safeDeleteUser(user);
                    displayWelcomeMenu();
                    break;
                case 0:
                    if (user.getRole() == User.Role.SELLER) {
                        sellerController.displayMainMenu(user);
                    } else {
                        customerController.displayMainMenu(user);
                    }
                    break;
                default:
                    basicView.printChoiceInvalid();
                    validChoice = false;
                    break;
            }
        } while (!validChoice);
    }

    public String askNewUsername() {
        String username;
        while(true) {
            username = userView.askUsername();
            if (userService.usernameExists(username)) {
                userView.displayUsernameExisted();
            } else {
                break;
            }
        }
        return username;
    }

    public String askNewEmail() {
        String email;
        while(true) {
            email = userView.askEmail();
            if (userService.emailExists(email)) {
                userView.displayEmailExisted();
            } else {
                break;
            }
        }
        return email;
    }

    public String askNewPassword() {
        String password;
        while(true) {
            password = userView.askPassword();
            if (userService.passwordInvalid(password)) {
                userView.displayPasswordInvalid();
            } else {
                break;
            }
        }
        return password;
    }

    public String askRole() {
        String role;
        while (true) {
            role = userView.askRole();
            if (role.equals("0") || role.equalsIgnoreCase("CUSTOMER") || role.equalsIgnoreCase("SELLER")) {
                break;
            } else {
                userView.displayRoleInvalid();
            }
        }
        return role;
    }
}
