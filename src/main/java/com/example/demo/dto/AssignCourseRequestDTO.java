package com.example.demo.dto;

import com.example.demo.models.Course;
import com.example.demo.models.Employee;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AssignCourseRequestDTO {
    private Integer empId;
    private Integer courseId;
    private Boolean status;
    private LocalDate startDate;
    private LocalDate endDate;
}
