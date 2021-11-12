package com.app.inventory.service;

import com.app.inventory.entity.Item;
import com.app.inventory.entity.Office;
import com.app.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void addInventory(Item item) {
        inventoryRepository.saveInventory(item);
    }

}
