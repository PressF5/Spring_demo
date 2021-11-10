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
		item1.setNumber(1234567L);
		item1.setDescription("Монитор");
		item1.setCountItems(5);

		Item item2 = new Item();
		item1.setNumber(9876543L);
		item1.setDescription("Системник");
		item1.setCountItems(2);

		Item item3 = new Item();
		item1.setNumber(9182736L);
		item1.setDescription("Клавиатура");
		item1.setCountItems(20);

		List<Item> itemList = List.of(item1, item2, item3);

		Office office = new Office();

		office.setOfficeNumber(308);
		office.setItemList(itemList);

		service.addInventory(office);
	}

}
