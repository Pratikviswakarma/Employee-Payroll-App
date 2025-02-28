package dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data  // Generates Getters, Setters, toString, equals, and hashCode
@NoArgsConstructor  // Generates a No-Arg Constructor
@AllArgsConstructor  // Generates an All-Args Constructor
public class EmployeeDTO {
    private String name;
    private Double salary;
}