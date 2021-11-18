package com.app.inventory.service;

import com.app.inventory.entity.Item;
import com.app.inventory.entity.Office;
import com.app.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void addInventory(Item item) {
        inventoryRepository.saveInventory(item);
    }

    @Transactional
    public void moveInventory(int invNumber, int fromOffice, int toOffice, int countItems) { inventoryRepository.moveInventory(invNumber, fromOffice, toOffice, countItems); }

    @Transactional
    public void removeItemsFromOffice(int invNumber,  int countItems, int fromOffice) {inventoryRepository.removeItemFromOffice(invNumber, countItems, fromOffice);}

    @Transactional
    public List<Item> getItemsByOffice(int numberOffice) {return inventoryRepository.getItemsByOffice(numberOffice);}

}
