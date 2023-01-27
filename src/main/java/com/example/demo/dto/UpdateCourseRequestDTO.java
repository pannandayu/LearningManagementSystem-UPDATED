package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseRequestDTO {
    private Integer id;
    private Integer empId;
    private Boolean status;
    private Integer courseId;
}
