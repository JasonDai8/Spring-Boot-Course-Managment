package com.example.studentcourseregistration.service;

//importing Student entity, Student Repository, autowired and service annotation, List structure, optional
import com.example.studentcourseregistration.exception.BadRequestException;
import com.example.studentcourseregistration.model.Student;
import com.example.studentcourseregistration.model.Course;
import com.example.studentcourseregistration.repository.CourseRepository;
import com.example.studentcourseregistration.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//setting studentService as a service component
@Service
public class StudentService {
    //injecting the instance of student repository
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    //method to retrieve students based on courseName
    public List<Student> findAllStudentsByCourseName(String courseName) {
        return studentRepository.findAllStudentsByCourses_Name(courseName);
    }
    //method to find all students
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }
    //find students not in specific course
    public List<Student> findStudentsNotInCourse(String courseName) {
        return studentRepository.findStudentsNotInCourse(courseName);
    }
    //find students by id
    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }
    //deleting student from repository
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    //updating a student's information
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setCourses(updatedStudent.getCourses());
        return studentRepository.save(existingStudent);
    }
    //adding student
    public Student addStudent(Student student) {
        if (student.getId() != null) {
            throw new BadRequestException("Student ID must not be provided.");
        }
        Set<Course> courses = new HashSet<>();
        for (Course course : student.getCourses()) {
            Course existingCourse = courseRepository.findById(course.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Course not found"));
            courses.add(existingCourse);
        }
        student.setCourses(courses);
        return studentRepository.save(student);
    }


}
