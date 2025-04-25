// package com.example.labwork2.service;

// import com.example.labwork2.model.Course;
// import com.example.labwork2.repository.CourseRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class CourseService {
//     @Autowired
//     private CourseRepository courseRepository;

//     public List<Course> getAllCourses(){
//         return courseRepository.findAll();
//     }

//     public Optional<Course> getCourseById(Long id){
//         return courseRepository.findById(id);
//     }

//     public Course createCourse(Course course){
//         return courseRepository.save(course);
//     }

//     public Course updateCourse(Long id, Course course){
//         course.setId(id);
//         return courseRepository.save(course);
//     }

//     public void deleteCourse(Long id){
//         courseRepository.deleteById(id);
//     }
// }

// package com.example.labwork2.service;

// import com.example.labwork2.model.Course;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

// import java.util.Optional;

// public interface CourseService {
//     Page<Course> getAllCourses(Pageable pageable);
//     Optional<Course> getCourseById(Long id);
//     Course createCourse(Course course);
//     Course updateCourse(Long id, Course course);
//     void deleteCourse(Long id);
// }
package com.example.labwork2.service;

import com.example.labwork2.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CourseService {
    Optional<Course> getCourseById(Long id);
    Page<Course> getAllCourses(Pageable pageable);
    Course createCourse(Course course);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);

    // Новый метод для фильтрации
    Page<Course> filterCourses(String name, String description, String nameLike, Pageable pageable);
}

