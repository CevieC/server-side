package sg.edu.nus.iss.day13work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/pagea")
    public String PageA(Model m, HttpSession session){
        
        if (session.getAttribute("myFullName") != null){
            m.addAttribute("SessionData", session.getAttribute("myFullName").toString());
        } else {
            m.addAttribute("SessionData", "There is no session object now");
        }
        return "pagea";
    }

    @PostMapping("/pagea")
    public String PageAPost(@RequestBody MultiValueMap<String, String> form, Model m, HttpSession session){
        // multi-value map is used to pass in the data since we don't have an object for this
        String myFullName = form.getFirst("fullname");
        System.out.println("My full name: " + myFullName);

        // a memory object is created, and will store the name i parse in
        // first thing to do is to create pageb
        session.setAttribute("myFullName", myFullName);

        // injecting the session's data into temporary model and return it to page b
        m.addAttribute("myName", session.getAttribute("myFullName").toString());
        return "pageb";
    }

    @GetMapping("/pageb")
    public String PageB(Model m, HttpSession session){

        // we take the information from page b to page c using session
        String myFullName = session.getAttribute("myFullName").toString();
        m.addAttribute("myFullName", myFullName);

        return "pagec";
    }

    @PostMapping("/pagec")
    public String PageC(Model m, HttpSession session){
        // clears the session, removes the attributes
        session.invalidate();

        return "pagea";
    }

    
}
