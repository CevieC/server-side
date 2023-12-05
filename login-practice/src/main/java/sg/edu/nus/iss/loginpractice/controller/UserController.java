package sg.edu.nus.iss.loginpractice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.loginpractice.models.User;

@Controller
@RequestMapping(path = " ")
public class UserController {

    @GetMapping(path={" ", "/index"})
    public String getInitLogin(){

        return "redirect:/";
    }

    @PostMapping("/register")
    public String signUp(Model model, HttpSession sess) {

        User user = new User();
        model.addAttribute("user", user);

        List<String> listOccupation = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listOccupation", listOccupation);

        sess.setAttribute("user", user);
        sess.setAttribute("listOccupation", listOccupation);

        return "signup";
    }

    @PostMapping("/register/userlist")
    public String formSubmit(@Valid @ModelAttribute("user") User user, BindingResult bindings, HttpSession sess){

        System.out.println(sess.getAttribute("username"));

        return "userlist";

    }
    
}
