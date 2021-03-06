package com.app.inventory.controller;

import com.app.inventory.entity.Item;
import com.app.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("session")
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

    @RequestMapping("/updateInventory")
    public String update(@ModelAttribute("item") Item item) {
        inventoryService.saveInventory(item);
        return "redirect:/search";
    }

    @RequestMapping("/moveInventory")
    public String move() {
        return "moveItems";
    }

    @RequestMapping("/movedInventory")
    public String moved(@RequestParam("fromOffice") int fromOffice, @RequestParam("toOffice") int toOffice,
                        @RequestParam("invNumber") int invNumber, @RequestParam("countItems") int countItems) {
        inventoryService.moveInventory(fromOffice, toOffice, invNumber, countItems);
        return "redirect:/moveInventory";
    }

    @RequestMapping("/removeInventory")
    public String remove() {
        return "removeItems";
    }

    @RequestMapping("/removedInventory")
    public String removed(@RequestParam("fromOffice") int fromOffice, @RequestParam("invNumber") int invNumber,
                          @RequestParam("countItems") int countItems) {
        inventoryService.removeItemsFromOffice(fromOffice, invNumber, countItems);
        return "redirect:/removeInventory";
    }

    @RequestMapping("/search")
    public String search() {
        return "search";
    }

//    @RequestMapping("/searched")
//    public String searched(@RequestParam("officeOrInvNumber") int search, @RequestParam("choice") String select, Model model) {
//        List<Item> items = inventoryService.getItemsByInvNumberOrOfficeNumber(search, select);
//        model.addAttribute("listItems", items);
//        return "searched :: resultsList";
//    }

    @RequestMapping("/updateInfo/{id}")
    public String updateInfo(@PathVariable("id") int itemId, Model model) {
        Item item = inventoryService.getItemById(itemId);
        model.addAttribute("item", item);
        return "update";
    }
}
