package service;

import dto.EmployeeDTO;
import model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j  // Lombok logging annotation
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeList.stream()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .orElse(null);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating new employee: {}", employeeDTO);
        Employee employee = new Employee(idCounter.getAndIncrement(), employeeDTO.getName(), employeeDTO.getSalary());
        employeeList.add(employee);
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        Optional<Employee> existingEmployee = employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();

        if (existingEmployee.isPresent()) {
            Employee emp = existingEmployee.get();
            emp.setName(employeeDTO.getName());
            emp.setSalary(employeeDTO.getSalary());
            log.info("Updated Employee: {}", emp);
            return new EmployeeDTO(emp.getName(), emp.getSalary());
        }
        log.warn("Employee with ID {} not found", id);
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        employeeList.removeIf(emp -> emp.getId().equals(id));
    }
}
