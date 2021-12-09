package com.app.inventory;

import com.app.inventory.model.Role;
import com.app.inventory.model.User;
import com.app.inventory.service.InventoryService;
import com.app.inventory.service_security.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication//(scanBasePackages = "com.app.inventory")
//@EnableAutoConfiguration
public class InventoryApplication {//extends SpringBootServletInitializer {
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(InventoryApplication.class);
//	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(InventoryApplication.class, args);
		InventoryService service = context.getBean("inventoryService", InventoryService.class);

//		Item item1 = new Item();
//		item1.setNumber(1234567);
//		item1.setDescription("Монитор_LG");
//		item1.setCountItems(5);
//
//		Item item2 = new Item();
//		item2.setNumber(9876543);
//		item2.setDescription("Системник");
//		item2.setCountItems(2);
//
//		Office office = new Office();
//		office.setOfficeNumber(700);
//
//		item1.setOffice(office);
//		item2.setOffice(office);
//
//		service.addInventory(item1);
//		service.addInventory(item2);

//		service.moveInventory(1137007, 700, 1500, 8);
//		service.moveInventory(1137007, 400, 600, 1);

//		service.removeItemsFromOffice(1137007, 3, 500);
//===============================================================================================================================================
		/*
		List<Item> items = service.getItemsByInvNumberOrOfficeNumber(700, "office");
		for(Item it: items)
			System.out.println("Number = " + it.getNumber() + " Description: " + it.getDescription() + " Count items = " + it.getCountItems() +
					" Office id = " + it.getOffice().getId() + " Office number = " + it.getOffice().getOfficeNumber());
		System.out.println("================================================================================");
		List<Item> items2 = service.getItemsByInvNumberOrOfficeNumber(1137007, "inv");
		for(Item it: items2)
			System.out.println("Number = " + it.getNumber() + " Description: " + it.getDescription() + " Count items = " + it.getCountItems() +
					" Office id = " + it.getOffice().getId() + " Office number = " + it.getOffice().getOfficeNumber());
		 */
//===============================================================================================================================================
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return arg -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//			userService.saveUser(new User(null, "John Travolta",
//					"john", "1234", new HashSet<>()));
//			userService.saveUser(new User(null, "Will Smith",
//					"will", "1234", new HashSet<>()));
//			userService.saveUser(new User(null, "Jim Carry",
//					"jim", "1234", new HashSet<>()));
//			userService.saveUser(new User(null, "Arnold Schwarzenegger",
//					"arnold", "1234", new HashSet<>()));
//
//			userService.addRoleToUser("john", "ROLE_USER");
//			userService.addRoleToUser("john", "ROLE_MANAGER");
//			userService.addRoleToUser("will", "ROLE_MANAGER");
//			userService.addRoleToUser("jim", "ROLE_ADMIN");
//			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
//			userService.addRoleToUser("arnold", "ROLE_ADMIN");
//			userService.addRoleToUser("arnold", "ROLE_USER");
//		};
//	}
}