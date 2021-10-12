package springboot04com.zf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot04com.zf.dao.DepartmentDao;
import springboot04com.zf.dao.EmployeeDao;
import springboot04com.zf.pojo.Department;
import springboot04com.zf.pojo.Employee;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
     EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;




    @RequestMapping("/emps")
    public String getAllEmployee(Model model){

        Collection<Employee> allEmployee = employeeDao.getAllEmployee();

         model.addAttribute("all",allEmployee);
         
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();

          model.addAttribute("departments",departments);

        return "emp/add";
    }


    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);

        return "redirect:/emps";
    }



    @GetMapping("/emp/{id}")
    public String toEdit(@PathVariable("id")Integer id,Model model){

        Employee employeeById = employeeDao.getEmployeeById(id);

        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("departments",departments);

        model.addAttribute("emp",employeeById);

        return "emp/edit";
    }



    @PostMapping("/updateEmp")
    public String update(Employee employee){
        
          employeeDao.save(employee);

        return "redirect:/emps";
    }



    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){

        employeeDao.deleteEmployeeById(id);
        
        return "redirect:/emps";
    }

    @RequestMapping("/user/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        
        return "redirect:/index.html";
    }



}
