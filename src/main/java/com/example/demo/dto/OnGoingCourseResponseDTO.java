package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OnGoingCourseResponseDTO {
    private String courseName;
    private String description;
    private List<String> segment;
    private Integer totalModule;
}
