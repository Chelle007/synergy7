package com.example.binarfud;

import com.example.binarfud.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BinarfudApplication {

	public static void main(String[] args) {
		// Uncomment ini untuk initialize data & comment lagi
//		DataInitializer dataInitializer = SpringApplication.run(BinarfudApplication.class, args).getBean(DataInitializer.class);
//		dataInitializer.initData();

		UserController userController = SpringApplication.run(BinarfudApplication.class, args).getBean(UserController.class);
		userController.displayWelcomeMenu();
	}
}

// Akun SELLER
// username = Seller
// password = 12345678

// Akun CUSTOMER
// username = Customer
// password = 12345678