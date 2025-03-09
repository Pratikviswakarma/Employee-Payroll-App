package model;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
@Data
public class Employee {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    private String gender;

	    private LocalDate startDate;

	    private String note;

	    private String profilePic;

	    private String department;

	    private Double salary;
}