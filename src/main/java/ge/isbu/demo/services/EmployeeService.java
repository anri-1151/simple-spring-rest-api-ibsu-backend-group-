package ge.isbu.demo.services;

import ge.isbu.demo.entities.Employee;
import ge.isbu.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) throws Exception {
        return employeeRepository.findById(id).orElseThrow(() -> new Exception("RECORD_NOT_FOUND"));
    }

}
