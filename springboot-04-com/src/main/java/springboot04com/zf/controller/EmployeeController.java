package springboot04com.zf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot04com.zf.dao.EmployeeDao;
import springboot04com.zf.pojo.Employee;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
     EmployeeDao employeeDao;
    

    @RequestMapping("/emps")
    public String getAllEmployee(Model model){

        Collection<Employee> allEmployee = employeeDao.getAllEmployee();

         model.addAttribute("all",allEmployee);
         
        return "emp/list";
    }




}
