package sg.edu.nus.iss.day13work.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.day13work.model.Employee;
import sg.edu.nus.iss.day13work.repo.EmployeeRepo;

@Controller
@RequestMapping(path = "/employees")
public class EmployeeController {

    // with autowire, no need to create a new employee object anymore
    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("add")
    public String employeeAdd(Model m){

        Employee emp = new Employee();
        m.addAttribute("employee", emp);
        return "employeeadd";
    }

    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result, Model m){

        // result refers to the result of the form
        if(result.hasErrors()) {
            return "employeeadd";
        }

        return "";
    }
}
