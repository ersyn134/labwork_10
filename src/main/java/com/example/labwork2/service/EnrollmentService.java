// package com.example.labwork2.service;

// import com.example.labwork2.model.Enrollment;
// import com.example.labwork2.repository.EnrollmentRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class EnrollmentService {
//     @Autowired
//     private EnrollmentRepository enrollmentRepository;

//     public List<Enrollment> getAllEnrollments(){
//         return enrollmentRepository.findAll();
//     }

//     public Optional<Enrollment> getEnrollmentById(Long id){
//         return enrollmentRepository.findById(id);
//     }

//     public Enrollment createEnrollment(Enrollment enrollment){
//         return enrollmentRepository.save(enrollment);
//     }

//     public Enrollment updateEnrollment(Long id, Enrollment enrollment){
//         enrollment.setId(id);
//         return enrollmentRepository.save(enrollment);
//     }

//     public void deleteEnrollment(Long id){
//         enrollmentRepository.deleteById(id);
//     }
// }

// package com.example.labwork2.service;

// import com.example.labwork2.model.Enrollment;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

// import java.util.Optional;

// public interface EnrollmentService {
//     Page<Enrollment> getAllEnrollments(Pageable pageable);
//     Optional<Enrollment> getEnrollmentById(Long id);
//     Enrollment createEnrollment(Enrollment enrollment);
//     Enrollment updateEnrollment(Long id, Enrollment enrollment);
//     void deleteEnrollment(Long id);
// }
package com.example.labwork2.service;

import com.example.labwork2.model.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface EnrollmentService {
    Optional<Enrollment> getEnrollmentById(Long id);
    Page<Enrollment> getAllEnrollments(Pageable pageable);
    Enrollment createEnrollment(Enrollment enrollment);
    Enrollment updateEnrollment(Long id, Enrollment enrollment);
    void deleteEnrollment(Long id);

    // Новый метод для фильтрации
    Page<Enrollment> filterEnrollments(Long studentId, Long courseId, Pageable pageable);
}

