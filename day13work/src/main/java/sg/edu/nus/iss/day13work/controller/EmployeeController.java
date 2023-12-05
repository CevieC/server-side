package sg.edu.nus.iss.day13work.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/add")
    public String employeeAdd(Model m){

        Employee emp = new Employee();
        m.addAttribute("employee", emp);
        return "employeeadd";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result, Model m) throws FileNotFoundException{

        // result refers to the result of the form
        if(result.hasErrors()) {
            return "employeeadd";
        }

        Boolean returnResult = empRepo.saveEmployee(employeeForm);
        
        // redirect to this given endpoint
        return "redirect:/employees/list";
    }

    // because its a hyperlink, its a getmapping not a deletemapping
    @GetMapping("/employeedel/{email}")
    public String deleteEmployee(@PathVariable("email") String email){

        Employee emp = empRepo.findByEmail(email);
        Boolean result = empRepo.delete(emp);

        return "redirect:/employees/list";
    }

    @GetMapping("/employeeupdate/{email}")
    public String updateEmployee(@PathVariable("email") String email, Model m) {
        Employee emp = empRepo.findByEmail(email);
        m.addAttribute("employee", emp);

        return "employeeupdate";
    }

    @PostMapping("/updEmployee")
    public String updateEmployeeRecord(@Valid @ModelAttribute("employee") Employee emp, BindingResult result, Model m){

        if (result.hasErrors()) {
            return "employeeupdate";
        }
        empRepo.updateEmployee(emp);
        return "redirect:/employees/list";
    }

    @GetMapping("/list")
    public String employeeList(Model m){
        List<Employee> employees = empRepo.findAllEmployees();
        m.addAttribute("employees", employees);
        return "employeelist";
    }
}
