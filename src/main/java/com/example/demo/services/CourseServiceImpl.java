package com.example.demo.services;

import com.example.demo.models.Course;
import com.example.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(Integer id) {
        return courseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("course tidak ada"));
    }

    @Override
    public Boolean save(Course course) {
        courseRepository.save(course);
        return courseRepository.findById(course.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        courseRepository.deleteById(id);
        return !courseRepository.findById(id).isPresent();
    }

    @Override
    public Integer getCourseIdByName(String courseName) {
        return courseRepository.getCourseIdByName(courseName);
    }

}
