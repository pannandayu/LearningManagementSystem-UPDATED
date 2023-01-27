package com.example.demo.services;


import com.example.demo.dto.AssignCourseRequestDTO;
import com.example.demo.dto.LearningCourseResponseDTO;
import com.example.demo.dto.SegmentResponseDTO;
import com.example.demo.dto.UpdateCourseRequestDTO;
import com.example.demo.models.Employee;
import com.example.demo.models.EmployeeCourse;
import com.example.demo.models.Segment;

import java.util.List;

public interface EmployeeCourseService {
    public List<EmployeeCourse> getAll();
    public EmployeeCourse getById(Integer id);
    public Boolean save(AssignCourseRequestDTO dto, Integer empId);
    public Boolean update(UpdateCourseRequestDTO dto, Integer empId);
    public Boolean revertUpdate(UpdateCourseRequestDTO dto, Integer empId);
    public Boolean delete(Integer id);
    public List<LearningCourseResponseDTO> getCourseByEmployeeIdDTO(Integer id);
    public List<SegmentResponseDTO> getSegmentByCourseIdDTO(Integer id);
    public EmployeeCourse getCourseByEmployeeId(Integer id);
    public List<Segment> getSegmentByCourseId(Integer id);
    public List<Module> getModuleBySegmentId(Integer id);
    public Integer getCourseIdByEmployeeId(Integer empId);
    public Integer updateCourseFinished(Integer empId, Integer courseId);
    public Boolean getFinishStatusByEmployeeId(Integer empId, Integer courseId);
}
