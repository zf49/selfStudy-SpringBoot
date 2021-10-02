package springboot04com.zf.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot04com.zf.pojo.Department;
import springboot04com.zf.pojo.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {


    private static Map<Integer, Employee> employees = null;

    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;


    static {

        employees = new HashMap<Integer,Employee>();

        employees.put(1001, new Employee(1001,"AA","1@qq.com",1,new Department(101,"教学部")));
        employees.put(1002, new Employee(1002,"BB","2@qq.com",0,new Department(102,"市场部")));
        employees.put(1003, new Employee(1003,"CC","3@qq.com",1,new Department(103,"教研部")));
        employees.put(1004, new Employee(1004,"DD","4@qq.com",0,new Department(104,"运营部")));
        employees.put(1005, new Employee(1005,"EE","5@qq.com",1,new Department(105,"后勤部")));

    }

    // 增加一个员工 ，主键自增
    private static Integer ininId = 1006;

    public  void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(ininId++);
        }

        employee.setDepartment( departmentDao.getDepartmentsById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }


//    查询所有员工信息
    public Collection<Employee> getAllEmployee(){
        return employees.values();
    }


    //通过id查询
    public Employee getEmployeeById(int id){
       return employees.get(id);
    }

}
