package com.example.binarfud;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BinarfudApplication {

	public static void main(String[] args) throws InterruptedException {
//		DataInitializer dataInitializer = SpringApplication.run(BinarfudApplication.class, args).getBean(DataInitializer.class);
//		dataInitializer.initData();

//		UserController userController = SpringApplication.run(BinarfudApplication.class, args).getBean(UserController.class);
//		userController.displayWelcomeMenu();

		SpringApplication.run(BinarfudApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

// Akun SELLER
// username = Seller
// password = 12345678

// Akun CUSTOMER
// username = Customer
// password = 12345678