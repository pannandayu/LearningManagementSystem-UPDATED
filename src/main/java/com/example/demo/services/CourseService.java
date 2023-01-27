package com.example.demo.services;

import com.example.demo.models.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getAll();
    public Course getById(Integer id);
    public Boolean save(Course course);
    public Boolean delete(Integer id);
    Integer getCourseIdByName(String courseName);
}
