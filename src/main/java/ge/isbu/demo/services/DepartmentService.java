package ge.isbu.demo.services;

import ge.isbu.demo.entities.Department;
import ge.isbu.demo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(Long id) throws Exception {
        return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RECORD_NOT_FOUND"));
    }

}
