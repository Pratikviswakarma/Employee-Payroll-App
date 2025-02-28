package service;

import dto.EmployeeDTO;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Generates unique IDs

    // Convert Entity to DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }

    // Convert DTO to Entity
    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        return new Employee(idCounter.getAndIncrement(), employeeDTO.getName(), employeeDTO.getSalary());
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        employeeList.add(employee);
        return convertToDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();

        if (existingEmployee.isPresent()) {
            Employee emp = existingEmployee.get();
            emp.setName(employeeDTO.getName());
            emp.setSalary(employeeDTO.getSalary());
            return convertToDTO(emp);
        }
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeList.removeIf(emp -> emp.getId().equals(id));
    }
}
