package com.example.demo.controllers;

import com.example.demo.dto.*;
import com.example.demo.models.Course;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.services.CourseService;
import com.example.demo.services.EmployeeCourseService;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.UserManagementDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/trainer")
public class RestTrainerController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeCourseService employeeCourseService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity<List<EmployeeResponseDTO>>
    getTrainerInfo(Authentication authentication) {
        String email = authentication.getName();
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getById(employee.getId()));
    }
    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<EmployeeResponseDTO>>
    getAllInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAll());
    }

    @GetMapping("/check/{id}")
    @ResponseBody
    public ResponseEntity<List<EmployeeResponseDTO>>
    checkStudent(@PathVariable("id") Integer id) throws NameNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(NameNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getById(employee.getId()));
    }

    @GetMapping("/check/{id}/progress")
    @ResponseBody
    public ResponseEntity<List<ModuleResponseDTO>>
    checkStudentProgress(@PathVariable("id") Integer id) throws NameNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(NameNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getModuleProgress(employee.getId()));
    }

    // COURSE PART
    @GetMapping("/course")
    @ResponseBody
    public ResponseEntity<List<Course>>
    getAllCourseInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAll());
    }

//    update course from 0 -> 1
//    @PutMapping("/course/update-course/{empId}")
//    @Transactional
//    public ResponseEntity<String> updateCourse(
//            @PathVariable("empId") Integer empId,
//            @RequestBody UpdateCourseRequestDTO dto) {
//        employeeCourseService.update(dto,empId);
//        return ResponseEntity.status(HttpStatus.OK).body("Course finished");
//    }

    // insert / assign course
    @PostMapping("/course/assign-course/{courseId}")
    public ResponseEntity<String> assignCourse(
            @PathVariable("courseId") Integer id,
            @RequestBody AssignCourseRequestDTO dto) {
        employeeCourseService.save(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body("Assign Course Success");
    }

    // buat path check employee progress di ongoing course
    
    @GetMapping("/course/{courseId}/unassigned")
    public List<AssignedEmployeeDTO> getUnassignedEmployees(@PathVariable("courseId") Integer id){

        return employeeService.getUnassignEmployees(id);
    }

    @GetMapping("/course/{courseId}/assigned")
    public List<AssignedEmployeeDTO> getAssignedEmployees(@PathVariable("courseId") Integer id){

        return employeeService.getAssignedEmployees(id);
    }
}
