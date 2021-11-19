package com.app.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/main")
    public String welcome() {
        return "main";
    }

    @RequestMapping("/view")
    public String view() {
        return "view1";
    }

}
