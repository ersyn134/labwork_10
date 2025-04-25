// package com.example.labwork2.specification;

// import com.example.labwork2.model.Enrollment;
// import org.springframework.data.jpa.domain.Specification;

// public class EnrollmentSpecification {

//     public static Specification<Enrollment> hasStudentId(Long studentId) {
//         return (root, query, cb) ->
//             studentId == null ? null : cb.equal(root.get("student").get("id"), studentId);
//     }
    
//     public static Specification<Enrollment> hasCourseId(Long courseId) {
//         return (root, query, cb) ->
//             courseId == null ? null : cb.equal(root.get("course").get("id"), courseId);
//     }
// }
package com.example.labwork2.specification;

import com.example.labwork2.model.Enrollment;
import org.springframework.data.jpa.domain.Specification;

public class EnrollmentSpecification {

    // Фильтр по идентификатору студента
    public static Specification<Enrollment> hasStudentId(Long studentId) {
        return (root, query, cb) ->
            studentId == null ? null : cb.equal(root.get("student").get("id"), studentId);
    }

    // Фильтр по идентификатору курса
    public static Specification<Enrollment> hasCourseId(Long courseId) {
        return (root, query, cb) ->
            courseId == null ? null : cb.equal(root.get("course").get("id"), courseId);
    }
}
