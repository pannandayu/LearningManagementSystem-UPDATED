package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeResponseDTO {

    private String fullName;
    private String email;
    private String roleName;
    private List<String> finishedCourse;
    private String ongoingCourse;

}
