package com.app.inventory.controller;

import com.app.inventory.entity.Item;
import com.app.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/main")
    public String main() {
        return "main";
    }





    @RequestMapping("/addInventory")
    public String add(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "addItems";
    }

    @RequestMapping("/saveInventory")
    public String save(@ModelAttribute("item") Item item) {
        inventoryService.saveInventory(item);
        return "redirect:/addInventory";
    }



    @RequestMapping("/moveInventory")
    public String move() {
        return "moveItems";
    }

    @RequestMapping("/removeInventory")
    public String remove() {
        return "removeItems";
    }






}
