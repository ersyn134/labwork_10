// package com.example.labwork2.service;

// import com.example.labwork2.model.Course;
// import com.example.labwork2.repository.CourseRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// public class CourseServiceImpl implements CourseService {

//     @Autowired
//     private CourseRepository courseRepository;
    
//     @Override
//     public Page<Course> getAllCourses(Pageable pageable) {
//         return courseRepository.findAll(pageable);
//     }
    
//     @Override
//     public Optional<Course> getCourseById(Long id) {
//         return courseRepository.findById(id);
//     }
    
//     @Override
//     public Course createCourse(Course course) {
//         return courseRepository.save(course);
//     }
    
//     @Override
//     public Course updateCourse(Long id, Course course) {
//         Optional<Course> optional = courseRepository.findById(id);
//         if(optional.isPresent()){
//             Course existing = optional.get();
//             existing.setName(course.getName());
//             existing.setDescription(course.getDescription());
//             return courseRepository.save(existing);
//         } else {
//             throw new RuntimeException("Course not found");
//         }
//     }
    
//     @Override
//     public void deleteCourse(Long id) {
//         courseRepository.deleteById(id);
//     }
// }
package com.example.labwork2.service;

import com.example.labwork2.model.Course;
import com.example.labwork2.repository.CourseRepository;
import com.example.labwork2.specification.CourseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    
    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
    
    @Override
    public Page<Course> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
    
    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
    
    @Override
    public Course updateCourse(Long id, Course course) {
        Optional<Course> optional = courseRepository.findById(id);
        if(optional.isPresent()){
            Course existing = optional.get();
            existing.setName(course.getName());
            existing.setDescription(course.getDescription());
            return courseRepository.save(existing);
        } else {
            throw new RuntimeException("Course not found");
        }
    }
    
    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
    
    @Override
    public Page<Course> filterCourses(String name, String description, String nameLike, Pageable pageable) {
        Specification<Course> spec = Specification.where(null);
        
        if (name != null) {
            spec = spec.and(CourseSpecification.hasName(name));
        }
        if (description != null) {
            spec = spec.and(CourseSpecification.hasDescription(description));
        }
        if (nameLike != null) {
            spec = spec.and(CourseSpecification.nameLike(nameLike));
        }
        
        return courseRepository.findAll(spec, pageable);
    }
}
