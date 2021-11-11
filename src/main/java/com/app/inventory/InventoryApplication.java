package com.app.inventory;

import com.app.inventory.entity.Item;
import com.app.inventory.entity.Office;
import com.app.inventory.service.InventoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(InventoryApplication.class, args);
		InventoryService service = context.getBean("inventoryService", InventoryService.class);

		Item item1 = new Item();
		item1.setNumber(1234567);
		item1.setDescription("Монитор");
		item1.setCountItems(5);

		Item item2 = new Item();
		item2.setNumber(9876543);
		item2.setDescription("Системник");
		item2.setCountItems(2);

		Item item3 = new Item();
		item3.setNumber(9182736);
		item3.setDescription("Клавиатура");
		item3.setCountItems(20);

		List<Item> itemList = List.of(item1, item2, item3);

		Office office = new Office();

		office.setOfficeNumber(400);
		office.setItemsToOffice(itemList);

		service.addInventory(office);
	}

}
