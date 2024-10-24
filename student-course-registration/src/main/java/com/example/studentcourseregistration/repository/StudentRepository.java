package com.example.studentcourseregistration.repository;

//importing student entity, jpaRepository, Param, repository annotation, List structure
import com.example.studentcourseregistration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

//setting up as repository
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //method should return a list of student objects based on course name
    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.name = :courseName ORDER BY s.name ASC")
    List<Student> findAllStudentsByCourses_Name(@Param("courseName") String courseName);
    //find all students
    List<Student> findAll();
    //finding students not registered for a course
    @Query("SELECT s FROM Student s WHERE s.id NOT IN (SELECT sc.id FROM Course c JOIN c.students sc WHERE c.name = :courseName)")
    List<Student> findStudentsNotInCourse(@Param("courseName") String courseName);


}
