package service;


import dto.EmployeeDTO;
import model.Employee;
import repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Convert Entity to DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }

    // Convert DTO to Entity
    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        return new Employee(null, employeeDTO.getName(), employeeDTO.getSalary());
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee savedEmployee = employeeRepository.save(convertToEntity(employeeDTO));
        return convertToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id).map(emp -> {
            emp.setName(employeeDTO.getName());
            emp.setSalary(employeeDTO.getSalary());
            return convertToDTO(employeeRepository.save(emp));
        }).orElse(null);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
