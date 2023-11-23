package sg.edu.nus.iss.day13work.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day13work.model.Employee;

@Repository
public class EmployeeRepo {
    
    final String dirPath = "c:\\data";
    final String fileName = "employee.txt";

    private List<Employee> employeeList;
    
    public EmployeeRepo() throws ParseException{
        if(employeeList == null)
            employeeList = new ArrayList<Employee>();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dt = df.parse("2002-10-01");
        Employee emp = new Employee("Cecila", "Chew",
        "cecilachewyc@gmail.com","96532064", 3500, dt,761671);
        employeeList.add(emp);

        emp = new Employee("Rachel", "Soh",
        "rchsoh23@gmail.com","98527362", 8000, dt,761672);
        employeeList.add(emp);
    }

    public List<Employee> findAllEmployees() {
        return employeeList;
    }

    public Boolean delete(Employee empDel) {
        // by default nothing is deleted
        Boolean result = false;

        // check if employees exists
        int employeeIdx = employeeList.indexOf(empDel);

        // if it exists, remove the employee based on the index
        if(employeeIdx >= 0) {
            employeeList.remove(employeeIdx);
            result = true;
        }
    
        return result;
    }

    public Employee findByEmail(String email){

        // find first will take the first record
        // .get() converts the type back to an object bc this function returns an employee object
        // list.stream().filter is important for query functions
        return employeeList.stream().filter(emp -> emp.getEmail().equals(email)).findFirst().get();
    }

    public Boolean saveEmployee(Employee empSave) throws FileNotFoundException {
        Boolean result = false;

        result = employeeList.add(empSave);
        File f = new File(dirPath + "/" + fileName);
        OutputStream os = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(os);
        pw.println(empSave.toString());
        pw.flush();
        pw.close();

        return result;
    }

    public Boolean updateEmployee(Employee empUpdate) {
        Boolean result = false;

        // retrieve object originally in storage
        Employee empObj = employeeList.stream().filter(emp -> emp.getEmail().equals(empUpdate.getEmail())).findFirst().get();

        int employeeIdx = employeeList.indexOf(empObj);

        if(employeeIdx >=0){
            employeeList.get(employeeIdx).setFirstName(empUpdate.getFirstName());
            employeeList.get(employeeIdx).setLastName(empUpdate.getLastName());
            employeeList.get(employeeIdx).setBirthDate(empUpdate.getBirthDate());
            employeeList.get(employeeIdx).setEmail(empUpdate.getEmail());
            employeeList.get(employeeIdx).setPhoneNo(empUpdate.getPhoneNo());
            employeeList.get(employeeIdx).setSalary(empUpdate.getSalary());
            employeeList.get(employeeIdx).setPostalCode(empUpdate.getPostalCode());
            result = true;
        }

        return result;
    }
}