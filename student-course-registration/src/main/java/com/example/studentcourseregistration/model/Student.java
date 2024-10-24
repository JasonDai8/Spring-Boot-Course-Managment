package com.example.studentcourseregistration.model;

//import all necessary tools
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

//making student class an entity here
//mapping the class to "student" on the table
@Entity
@Table(name = "student")
public class Student {
    @Id
    //tells JPA to generate primary keys when adding new students, IDENTITY allows auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //use long here instead of int, for scalability
    private Long id;
    private String name;
    //no arg constructor for entity JPA
    public Student() {
    }

    //many-to-many relationship, allows student to be enrolled in multiple courses, course has multiple students
    //cascade allows for saving and deleting to affect both students and courses
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    //set of courses for student
    private Set<Course> courses = new HashSet<>();

    //getters and setters
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    public Set<Course> getCourses() {
        return this.courses;
    }
    public void addCourse(Course course) {
        this.courses.add(course);
        course.getStudents().add(this);
    }
    public void removeCourse(Course course) {
        this.courses.remove(course);
        course.getStudents().remove(this);
    }


    //overriding equals and hashcode implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id != null && id.equals(student.id);
    }
    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }
}
