// package com.example.labwork2.repository;

// import com.example.labwork2.model.Course;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface CourseRepository extends JpaRepository<Course, Long> {
// }
package com.example.labwork2.repository;

import com.example.labwork2.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
}
