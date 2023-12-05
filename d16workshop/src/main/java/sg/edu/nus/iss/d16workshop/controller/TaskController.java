package sg.edu.nus.iss.d16workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TaskController {
    
    @GetMapping
    public String getTask(){

        return "redirect:index.html";
    }
}
