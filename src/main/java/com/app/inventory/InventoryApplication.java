package com.app.inventory;

import com.app.inventory.entity.Item;
import com.app.inventory.service.InventoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

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

		List<Item> items = service.getItemsByInvNumberOrOfficeNumber(700, "office");
		for(Item it: items)
			System.out.println("Number = " + it.getNumber() + " Description: " + it.getDescription() + " Count items = " + it.getCountItems() +
					" Office id = " + it.getOffice().getId() + " Office number = " + it.getOffice().getOfficeNumber());
		System.out.println("================================================================================");
		List<Item> items2 = service.getItemsByInvNumberOrOfficeNumber(1137007, "inv");
		for(Item it: items2)
			System.out.println("Number = " + it.getNumber() + " Description: " + it.getDescription() + " Count items = " + it.getCountItems() +
					" Office id = " + it.getOffice().getId() + " Office number = " + it.getOffice().getOfficeNumber());
	}
}