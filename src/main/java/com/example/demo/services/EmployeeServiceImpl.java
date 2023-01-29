package com.example.demo.services;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.dto.*;
import com.example.demo.models.User;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    // employee data
    private EmployeeResponseDTO convertEmployeeToDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        Integer id = employee.getId();

        dto.setFullName(employee.getFullname());
        dto.setEmail(employee.getEmail());
        dto.setRoleName(employee.getUser().getRole().getName());

        String onGoing = employeeRepository.getOnGoingCourse(id);
        List<String> finished = employeeRepository.getFinishedCourse(id);

        if(finished.isEmpty()) {
            dto.setFinishedCourse(List.of("No course has been finished yet."));
        } else {
            dto.setFinishedCourse(employeeRepository.getFinishedCourse(id));
        }

        if(onGoing == null) {
            dto.setOngoingCourse("Currently there are no on-going course.");
        } else {
            dto.setOngoingCourse(onGoing);
        }
        return dto;
    }

    // on going course data
    private OnGoingCourseResponseDTO convertOnGoingCourseDataToDTO(Employee employee) {
        OnGoingCourseResponseDTO dto = new OnGoingCourseResponseDTO();

        String courseName = employeeRepository.getOnGoingCourse(employee.getId());
        String courseDesc = employeeRepository.getOngoingCourseDescription(courseName);
        List<String> courseSegment = employeeRepository.getSegmentPerOnGoingCourse(courseName);
        Integer courseModule = employeeRepository.getModulePerOnGoingCourse(courseName);

        dto.setCourseName(courseName);
        dto.setDescription(courseDesc);
        dto.setSegment(courseSegment);
        dto.setTotalModule(courseModule);
        return dto;
    }

    // on going course progress (per module count)
    private ModuleResponseDTO convertOnGoingCourseProgressToDTO(Employee employee) {
        ModuleResponseDTO dto = new ModuleResponseDTO();

        String courseName = employeeRepository.getOnGoingCourse(employee.getId());
        Integer moduleFinished = employeeRepository.totalModuleFinished(employee.getId());
        Integer courseModule = employeeRepository.getModulePerOnGoingCourse(courseName);

        if(moduleFinished != null) {
            dto.setFinishedModuleCount(moduleFinished);
            dto.setModuleRemainingCount(courseModule - moduleFinished);
            dto.setTotalModuleCount(courseModule);
        } else {
            dto.setFinishedModuleCount(0);
            dto.setModuleRemainingCount(courseModule);
            dto.setTotalModuleCount(courseModule);
        }
        return dto;
    }

    // finished course data (history)
    private List<FinishedCourseResponseDTO> convertFinishedCourseDataToDTO(Employee employee) {

        List<String> listOfCourseName = employeeRepository.getObjectFinishedCourse(employee.getId());
        List<FinishedCourseResponseDTO> list = new ArrayList<>();

        for (int i = 0; i < listOfCourseName.size(); i++) {
            FinishedCourseResponseDTO dto = new FinishedCourseResponseDTO();

            String courseName = listOfCourseName.get(i);
            Integer courseId = employeeRepository.getFinishedCourseIdByName(courseName);
            LocalDate courseEndDate = employeeRepository.getFinishedCourseDateById(courseId);
            Integer finishedModuleCount = employeeRepository.getFinishedCourseModuleCountById(courseId);

            dto.setCourseName(courseName);
            dto.setEndDate(courseEndDate);
            dto.setModuleCount(finishedModuleCount);

            list.add(dto);
        }
        return list;
    }

    @Override
    public List<FinishedCourseResponseDTO> getFinishedCourseData(Integer id) {
        return employeeRepository
                .findById(id)
                .stream()
                .map(this::convertFinishedCourseDataToDTO)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public void insertCourseStudent(Integer empId, Integer courseId, Boolean status, LocalDate startDate, LocalDate endDate) {
    }

    @Override
    public List<ModuleResponseDTO> getModuleProgress(Integer id) {
        return employeeRepository
                .findById(id)
                .stream()
                .map(this::convertOnGoingCourseProgressToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OnGoingCourseResponseDTO> getOnGoingCourseData(Integer id) {
        return employeeRepository
                .findById(id)
                .stream()
                .map(this::convertOnGoingCourseDataToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponseDTO> getAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(this::convertEmployeeToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponseDTO> getById(Integer id) {
        return employeeRepository
                .findById(id)
                .stream()
                .map(this::convertEmployeeToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean save(RegisterRequestDTO dto) {
        if(dto != null) {
            Employee employee = new Employee();
            User user = new User();

            employee.setId(dto.getId());
            employee.setEmail(dto.getEmail());
            employee.setFullname(dto.getFullName());
            employee.setBirthdate(dto.getBirthDate());
            employee.setAddress(dto.getAddress());
            employee.setIstrainer(false);
            employeeRepository.save(employee);

            user.setId(employee.getId());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setRole(roleRepository.getMaxLevelRole());
            userRepository.save(user);
            return employeeRepository.findById(employee.getId()).isPresent();
        }
        return false;
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email);
    }

    @Override
    public List<String> getFinishedCourse(Integer id) {
        return employeeRepository.getFinishedCourse(id);
    }

    @Override
    public List<Date> getFinishDateCourse(Integer id) {
        return employeeRepository.getFinishDateCourse(id);
    }

    @Override
    public List<Integer> getTotalModuleFinishedCourse(Integer id) {
        return employeeRepository.getTotalModuleFinishedCourse(id);
    }

    @Override
    public String getOnGoingCourse(Integer id) {
        return employeeRepository.getOnGoingCourse(id);
    }

    @Override
    public List<String> getSegmentPerOnGoingCourse(String courseName) {
        return employeeRepository.getSegmentPerOnGoingCourse(courseName);
    }

    @Override
    public String getOngoingCourseDescription(String courseName) {
        return employeeRepository.getOngoingCourseDescription(courseName);
    }

    @Override
    public Integer getModulePerOnGoingCourse(String courseName) {
        return employeeRepository.getModulePerOnGoingCourse(courseName);
    }

    @Override
    public Integer totalModuleFinished(Integer id) {
        return employeeRepository.totalModuleFinished(id);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("employee not found"));
    }

    @Override
    public Boolean delete(Integer id) {
        employeeRepository.deleteById(id);
        return employeeRepository.findById(id).isEmpty();
    }

    @Override
    @Transactional
    public void updateCourseStudent(Boolean status, Integer empId, Integer courseId) {
        employeeRepository.updateCourseStudent(status, empId, courseId);
    }

}
