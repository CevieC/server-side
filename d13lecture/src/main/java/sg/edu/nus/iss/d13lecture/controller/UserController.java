package sg.edu.nus.iss.d13lecture.controller;

import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/users")
    public String CreateUser(@RequestBody MultiValueMap<String, String> form, Model m){
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");

        System.out.println("name: " + name);
        System.out.println("email: " + email);
        System.out.println("phone: " + phone);

        return "";
    }
    
}
