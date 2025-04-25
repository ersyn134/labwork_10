// package com.example.labwork2.repository;

// import com.example.labwork2.model.Enrollment;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
// }
package com.example.labwork2.repository;

import com.example.labwork2.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>, JpaSpecificationExecutor<Enrollment> {
}
