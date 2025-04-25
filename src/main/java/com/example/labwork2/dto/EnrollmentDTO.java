package com.example.labwork2.dto;

public class EnrollmentDTO {
    private Long id;
    private CourseDTO course;
    private StudentDTO student;

    // Конструкторы
    public EnrollmentDTO() {}

    public EnrollmentDTO(Long id, CourseDTO course, StudentDTO student) {
        this.id = id;
        this.course = course;
        this.student = student;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }
}
