package service;


import dto.EmployeeDTO;

import exception.EmployeeNotFoundException;
import model.Employee;
import repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    public List<Employee> getEmployeesByDepartment() {
        log.info("Getting employees by department: Sales");
        return employeeRepository.findEmployeesByDepartment();
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        log.info("Getting all employees");
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Employee getEmployeeById(Long id) {
        log.info("Getting employee by ID: {}", id);
        return employeeRepository.findById(id).orElse(null); // Returns null if not found
    }

    // Create employee
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating new employee: {}", employeeDTO.getName());
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setGender(employeeDTO.getGender());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setNote(employeeDTO.getNote());
        employee.setProfilePic(employeeDTO.getProfilePic());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    // Update employee by ID
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee by ID: {}", id);
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(employeeDTO.getName());
            employee.setGender(employeeDTO.getGender());
            employee.setStartDate(employeeDTO.getStartDate());
            employee.setNote(employeeDTO.getNote());
            employee.setProfilePic(employeeDTO.getProfilePic());
            employee.setDepartment(employeeDTO.getDepartment());
            employee.setSalary(employeeDTO.getSalary());
            return employeeRepository.save(employee);
        }
        log.warn("Employee not found with ID: {}", id);
        return null; // Employee not found
    }

    // Delete employee by ID
    public boolean deleteEmployee(Long id) {
        log.info("Deleting employee by ID: {}", id);
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            log.info("Employee deleted successfully");
            return true;
        }
        log.warn("Employee not found with ID: {}", id);
        return false; // Employee not found
    }
}