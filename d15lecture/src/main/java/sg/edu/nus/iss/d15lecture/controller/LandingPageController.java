package sg.edu.nus.iss.d15lecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.d15lecture.model.Item;

// @Controller
// @RequestMapping(path = {"/","/index.html"})
public class LandingPageController {
    
    //@GetMapping
    public String getIndex(Model m){

        m.addAttribute("Item", new Item());
        return "index";
    }
}
