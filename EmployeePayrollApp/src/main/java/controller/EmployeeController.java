package controller;



import dto.EmployeeDTO;
import model.Employee;
import service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	  private final EmployeeService employeeService;

	    @Autowired
	    public EmployeeController(EmployeeService employeeService) {
	        this.employeeService = employeeService;
	    }

	    @GetMapping
	    public List<Employee> getAllEmployees() {
	        return employeeService.getAllEmployees();
	    }

	    @GetMapping("/{id}")
	    public Employee getEmployeeById(@PathVariable Long id) {
	        return employeeService.getEmployeeById(id);
	    }

	    @PostMapping
	    public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO) {
	        return employeeService.createEmployee(employeeDTO);
	    }

	    @PutMapping("/{id}")
	    public Employee updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
	        return employeeService.updateEmployee(id, employeeDTO);
	    }

	    @DeleteMapping("/{id}")
	    public boolean deleteEmployee(@PathVariable Long id) {
	        return employeeService.deleteEmployee(id);
	    }
}