package sg.edu.nus.iss.d11lecture.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.ExpressionFactoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.edu.nus.iss.d11lecture.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
     
    //@RequestMapping(value = "/list", method = RequestMethod.GET)
    @GetMapping("/list")
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ashley","Chew","ashchew123@gmail.com.", "82731838"));
        employees.add(new Employee("Cc","Chew","Ccchew123@gmail.com.", "96532064"));

        return employees;
    }
}
