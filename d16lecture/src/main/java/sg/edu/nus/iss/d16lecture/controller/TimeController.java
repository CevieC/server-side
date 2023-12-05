package sg.edu.nus.iss.d16lecture.controller;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/time", produces = MediaType.TEXT_HTML_VALUE)
public class TimeController {

    @GetMapping
    public String getTime(Model m) {
        String currTime = (new Date()).toString();
        m.addAttribute("time", currTime);

        return "time";

    }

    
}
