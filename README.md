# Student Course Management System

## Overview

The Student Course Management System is a Spring Boot application designed to manage student registrations and course enrollments. This application allows users to create, read, update, and delete students and courses, as well as manage student registrations to courses. The application uses a RESTful API for communication and supports essential exception handling for smooth operation.

## Features

- **Student Management**: Add, update, retrieve, and delete student information.
- **Course Management**: Add, update, retrieve, and delete course information.
- **Enrollment Management**: Register students to courses and retrieve lists of enrolled students.
- **Search Functionality**: Find students not enrolled in a specific course.
- **Exception Handling**: Comprehensive error handling for better user experience.
- **Database Support**: Utilizes JPA and Hibernate for database interactions.

## Technologies Used

- **Java**: The primary programming language.
- **Spring Boot**: Framework for building the RESTful web application.
- **Spring Data JPA**: For database interactions and ORM.
- **Hibernate**: The underlying JPA implementation.
- **MySQL**: Database management system (can be replaced with another DB if needed).
- **Maven**: Build tool for dependency management.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git

##Bonus

-  **What if we want to record course scores?**: We would update Course entity to have a field called scores, add a relationship to the Student entity, and implement methods to manipulate the score entity in the repository. Finally, add endpoints for getting score, posting score, and updating score in the controller.
-  **How to find all students who don't register for a given course?**: Implement a method that finds the students not in the course by using course id and checking equality 
