package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_tr_employeecourse")
public class EmployeeCourse {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "startdate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate Startdate;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "enddate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate Enddate;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
