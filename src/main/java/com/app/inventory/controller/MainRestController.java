package com.app.inventory.controller;

import com.app.inventory.entity.Item;
import com.app.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping(value = "/searchItems")
    public List<Item> searched(@RequestParam("officeOrInvNumber") int search, @RequestParam("choice") String select) {
        return inventoryService.getItemsByInvNumberOrOfficeNumber(search, select);
    }

    @GetMapping("/deleteItem/{id}")
    public void deleteItem(@PathVariable("id") int itemId) {
        inventoryService.deleteItemById(itemId);
        //return "redirect:/search";
    }
}