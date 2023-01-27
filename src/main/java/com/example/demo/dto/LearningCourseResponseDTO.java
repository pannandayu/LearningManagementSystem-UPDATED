package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LearningCourseResponseDTO {
    private Integer empId;
    private Integer courseId;
    private String name;
    private String description;
}
