package ge.isbu.demo.controllers;

import ge.isbu.demo.Util.GeneralUtil;
import ge.isbu.demo.dto.AddEmployee;
import ge.isbu.demo.dto.SearchEmployee;
import ge.isbu.demo.dto.request.RequestData;
import ge.isbu.demo.entities.Employee;
import ge.isbu.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/employees")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //@PreAuthorize("hasAuthority('employee:read')")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    //@PreAuthorize("hasAuthority('employee:read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Employee getById(@PathVariable Long id) throws Exception {
        return employeeService.getById(id);
    }

    //@PreAuthorize("hasAuthority('employee:add')")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Employee add(@RequestBody AddEmployee addEmployee) throws Exception {
        GeneralUtil.checkRequiredProperties(addEmployee, Arrays.asList("firstName", "lastName", "departmentId"));
        return employeeService.add(addEmployee);
    }

    //@PreAuthorize("hasAuthority('employee:add')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Employee edit(@PathVariable Long id, @RequestBody AddEmployee addEmployee) throws Exception {
        GeneralUtil.checkRequiredProperties(addEmployee, Arrays.asList("firstName", "lastName", "departmentId"));
        return employeeService.edit(id, addEmployee);
    }

    //@PreAuthorize("hasAuthority('employee:read')")
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"})
    public Slice<Employee> search(@RequestBody RequestData<SearchEmployee> rd) {
        return employeeService.search(rd.getData(), rd.getPaging());
    }

}
