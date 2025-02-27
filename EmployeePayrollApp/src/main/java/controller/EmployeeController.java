package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import model.Employee;

@RestController
@RequestMapping("/employees")
class EmployeeController {
    private List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employees.set(id, employee);
        return employee;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employees.remove(id);
        return "Employee deleted";
    }
}

