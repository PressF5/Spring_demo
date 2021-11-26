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
    public void saveInventory(Item item) {
        inventoryRepository.saveInventory(item);
    }

    @Transactional
    public void moveInventory(int fromOffice, int toOffice, int invNumber, int countItems) { inventoryRepository.moveInventory(fromOffice, toOffice, invNumber, countItems); }

    @Transactional
    public void removeItemsFromOffice(int fromOffice, int invNumber, int countItems) {inventoryRepository.removeItemFromOffice(fromOffice, invNumber, countItems);}

    @Transactional
    public List<Item> getItemsByInvNumberOrOfficeNumber(int invNumberOrNumberOffice, String type) {
        if("office".equals(type))
            return inventoryRepository.getItemsByOffice(invNumberOrNumberOffice);
        else
            return inventoryRepository.getItemsByInvNumber(invNumberOrNumberOffice);
    }
}
