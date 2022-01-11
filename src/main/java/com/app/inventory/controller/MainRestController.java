package com.app.inventory.controller;

import com.app.inventory.entity.Item;
import com.app.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "/searchItems", method = RequestMethod.POST)
    public List<Item> searched(@RequestParam("officeOrInvNumber") int search, @RequestParam("choice") String select) {
        return inventoryService.getItemsByInvNumberOrOfficeNumber(search, select);
    }
}
