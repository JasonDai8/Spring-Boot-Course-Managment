package com.example.studentcourseregistration.service;

import com.example.studentcourseregistration.model.Course;
import com.example.studentcourseregistration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

}
