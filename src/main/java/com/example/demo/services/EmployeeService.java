package com.example.demo.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.demo.dto.*;
import com.example.demo.models.Employee;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAll();
    List<EmployeeResponseDTO> getById(Integer id);
    Boolean save(RegisterRequestDTO dto);
    Boolean delete(Integer id);
    void updateCourseStudent(Boolean status, Integer empId, Integer courseId);
    List<ModuleResponseDTO> getModuleProgress(Integer id);
    List<OnGoingCourseResponseDTO> getOnGoingCourseData(Integer id);
    Employee findEmployeeByEmail(String email);
    List<String> getFinishedCourse(Integer id);
    List<Date> getFinishDateCourse(Integer id);
    List<Integer> getTotalModuleFinishedCourse(Integer id);
    String getOnGoingCourse(Integer id);
    List<String> getSegmentPerOnGoingCourse(String courseName);
    Integer getModulePerOnGoingCourse(String courseName);
    String getOngoingCourseDescription(String courseName);
    Integer totalModuleFinished(Integer id);
    Employee findById(Integer id);
    List<FinishedCourseResponseDTO> getFinishedCourseData(Integer id);
    void insertCourseStudent(Integer empId, Integer courseId, Boolean status, LocalDate startDate, LocalDate endDate);
}
