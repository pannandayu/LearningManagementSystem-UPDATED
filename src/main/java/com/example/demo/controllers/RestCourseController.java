package com.example.demo.controllers;

import com.example.demo.dto.AssignCourseRequestDTO;
import com.example.demo.models.Course;
import com.example.demo.models.Employee;
import com.example.demo.models.EmployeeCourse;
import com.example.demo.services.CourseService;
import com.example.demo.services.EmployeeCourseService;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class RestCourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeCourseService employeeCourseService;


    @GetMapping
    public List<Course> index(Model model) {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable(required = true) Integer id) {
        return courseService.getById(id);
    }

    @PostMapping("/update/{id}")
    public Boolean update(@PathVariable(required = true) Integer id, @RequestBody Course course) {
        course.setId(id);
        Boolean result = courseService.save(course);

        return result;
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable(required = true) Integer id) {
        Boolean result = courseService.delete(id);

        return result;
    }

    @PostMapping("/save")
    public Boolean save(Course course) {
        Boolean result;
        result = courseService.save(course);

        return result;
    }

//    @PutMapping("/assign/{id}")
//    public ResponseEntity<String> assign(@PathVariable(required = true) Integer id,
//                                         @RequestBody AssignCourseRequestDTO assignRequest) {
//        Employee employee = employeeService.findById(assignRequest.getEmpId().getId());
//
//        EmployeeCourse employeeCourse = new EmployeeCourse();
//        employeeCourse.setCourse(courseService.getById(id));
//        employeeCourse.setEmployee(employee);
//        employeeCourse.setStartdate(assignRequest.getStartDate());
//        employeeCourse.setEnddate(assignRequest.getEndDate());
//        employeeCourse.setStatus(false);
//
//        Boolean result = employeeCourseService.save(employeeCourse);
//
//        return ResponseEntity.status(HttpStatus.OK).body("Assign Course Successful");
//    }

}
