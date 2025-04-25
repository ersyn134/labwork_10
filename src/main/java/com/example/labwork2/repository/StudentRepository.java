// package com.example.labwork2.repository;

// import com.example.labwork2.model.Student;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface StudentRepository extends JpaRepository<Student, Long> {
// }
package com.example.labwork2.repository;

import com.example.labwork2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
}
