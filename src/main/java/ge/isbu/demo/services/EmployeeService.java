package ge.isbu.demo.services;

import ge.isbu.demo.Util.GeneralUtil;
import ge.isbu.demo.dto.AddEmployee;
import ge.isbu.demo.entities.Department;
import ge.isbu.demo.entities.Employee;
import ge.isbu.demo.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) throws Exception {
        return employeeRepository.findById(id).orElseThrow(() -> new Exception("RECORD_NOT_FOUND"));
    }

    @Transactional
    public Employee add(AddEmployee addEmployee) throws Exception {
        Employee employee = new Employee();
//        employee.setFirstName(addEmployee.getFirstName());
//        employee.setLastName(addEmployee.getLastName());
//        employee.setMiddleName(addEmployee.getMiddleName());
//        employee.setHireDate(addEmployee.getHireDate());
//        employee.setSalary(addEmployee.getSalary());
        GeneralUtil.getCopyOf(addEmployee, employee);

        Department department = departmentService.getById(addEmployee.getDepartmentId());

        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee edit(Long id, AddEmployee addEmployee) throws Exception {
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception("EMPLOYEE_NOT_FOUND"));
            //update employee data
            GeneralUtil.getCopyOf(addEmployee, employee);
            if (addEmployee.getDepartmentId() != null && !addEmployee.getDepartmentId().equals(employee.getDepartment().getDepartmentId())) {
                Department department = departmentService.getById(addEmployee.getDepartmentId());
                employee.setDepartment(department);
            }
            return employeeRepository.save(employee);
    }
}
