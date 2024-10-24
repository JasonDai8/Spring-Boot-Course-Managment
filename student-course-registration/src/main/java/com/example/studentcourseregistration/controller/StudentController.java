package com.example.studentcourseregistration.controller;

//importing Student, StudentService, Autowired, ResponseEntity, and all annotations (this is for REST API)
import com.example.studentcourseregistration.model.Student;
import com.example.studentcourseregistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

//setting up the controller
@RestController
@RequestMapping("/students")
public class StudentController {

    //injecting instance
    @Autowired
    private StudentService studentService;
    //get request to return list of all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }
    //maps a get request to /students/{id}, retrieves student by ID and returns response
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentByID(@PathVariable Long id) {
        Optional<Student> student = studentService.findStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
    }
    //maps post request to /students, expects JSON representation of Student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
    //maps a put request to /students/{id}, updates the student information based on ID provided
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }
    //maps delete request to /students/{id}, deletes the student with given ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    //maps a get request to /students/course/{courseName}, retrieves students enrolled
    @GetMapping("/course/{courseName}")
    public List<Student> getAllStudentsByCourseName(@PathVariable String courseName) {
        return studentService.findAllStudentsByCourseName(courseName);
    }
    //maps a get request to /students/not-in-course/{courseName}, retrieves students who are not enrolled in course
    @GetMapping("/not-in-course/{courseName}")
    public List<Student> getStudentsNotInCourse(@PathVariable String courseName) {
        return studentService.findStudentsNotInCourse(courseName);
    }
}
