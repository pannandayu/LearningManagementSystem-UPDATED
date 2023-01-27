package com.example.demo.services;

import com.example.demo.dto.AssignCourseRequestDTO;
import com.example.demo.dto.LearningCourseResponseDTO;
import com.example.demo.dto.SegmentResponseDTO;
import com.example.demo.dto.UpdateCourseRequestDTO;
import com.example.demo.models.EmployeeCourse;
import com.example.demo.models.Segment;
import com.example.demo.repositories.EmployeeCourseRepository;
import com.example.demo.repositories.EmployeeRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeCourseServiceImpl implements EmployeeCourseService{

    @Autowired
    private EmployeeCourseRepository employeeCourseRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private LearningCourseResponseDTO convertToDto(EmployeeCourse employeeCourse){
        return new LearningCourseResponseDTO(employeeCourse.getEmployee().getId(),
                employeeCourse.getCourse().getId(),
                employeeCourse.getCourse().getName(),
                employeeCourse.getCourse().getDescription());
    }

    public SegmentResponseDTO viewSegmentByCourseId(Segment segment){
        return new SegmentResponseDTO(segment.getId(),
                segment.getName(),
                segment.getDescription(),
                segment.getCourse().getId());
    }

    @Override
    public List<EmployeeCourse> getAll() {
        return employeeCourseRepository.findAll();
    }

    @Override
    public EmployeeCourse getById(Integer id) {
        return employeeCourseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("employee course tidak ada"));
    }

    @Override
    @Transactional
    public Boolean save(AssignCourseRequestDTO dto, Integer empId) {
        dto.setStatus(false);
        dto.setEmpId(empId);
        employeeRepository.insertCourseStudent(
                dto.getEmpId(),
                dto.getCourseId(),
                dto.getStatus(),
                dto.getStartDate(),
                dto.getEndDate());
        return true;
    }

    @Override
    @Transactional
    public Boolean update(UpdateCourseRequestDTO dto, Integer empId) {
        dto.setStatus(true);
        dto.setEmpId(empId);
        employeeRepository.updateCourseStudent(
                dto.getStatus(),
                dto.getEmpId(),
                dto.getCourseId()
        );
        return true;
    }

    @Override
    @Transactional
    public Boolean revertUpdate(UpdateCourseRequestDTO dto, Integer empId) {
        dto.setStatus(false);
        dto.setEmpId(empId);
        employeeRepository.updateCourseStudent(
                dto.getStatus(),
                dto.getEmpId(),
                dto.getCourseId()
        );
        return true;
    }


    @Override
    public Boolean delete(Integer id) {
        employeeCourseRepository.deleteById(id);
        return !employeeCourseRepository.findById(id).isPresent();
    }

    @Override
    public List<LearningCourseResponseDTO> getCourseByEmployeeIdDTO(Integer id) {
        List<EmployeeCourse> employeeCourse = employeeCourseRepository.findCourseByEmployeeIdCheck(id);
        return employeeCourse.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<SegmentResponseDTO> getSegmentByCourseIdDTO(Integer id) {
        List<Segment> segments = employeeCourseRepository.findSegmentByCourseIdCheck(id);
        return segments.stream().map(this::viewSegmentByCourseId).collect(Collectors.toList());    }

    @Override
    public EmployeeCourse getCourseByEmployeeId(Integer id) {
        return employeeCourseRepository.findCourseByEmployeeId(id);
    }

    @Override
    public List<Segment> getSegmentByCourseId(Integer id) {
        return null;
    }

    @Override
    public List<Module> getModuleBySegmentId(Integer id) {
        return employeeCourseRepository.findModuleBySegmentId(id);
    }

    @Override
    public Integer getCourseIdByEmployeeId(Integer empId) {
        return employeeCourseRepository.getCourseIdByEmployeeId(empId);
    }

    @Override
    @Transactional
    public Integer updateCourseFinished(Integer empId, Integer courseId) {
        return 1;
    }

    @Override
    public Boolean getFinishStatusByEmployeeId(Integer empId, Integer courseId) {
        return employeeCourseRepository.getFinishStatusByEmployeeId(empId, courseId);
    }


}
