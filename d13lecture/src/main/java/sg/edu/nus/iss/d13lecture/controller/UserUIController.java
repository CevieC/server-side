package sg.edu.nus.iss.d13lecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/userview")
public class UserUIController {

    @GetMapping("/user")
    public String userForm(){
        return "createuser";
    }
    


}
