package com.example.demo.controllers;

import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/learning-course")
public class RestLearningCourseController {
    @Autowired
    private EmployeeCourseService employeeCourseService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SegmentService segmentService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProgressService progressService;



}
