// package com.example.labwork2.specification;

// import com.example.labwork2.model.Course;
// import org.springframework.data.jpa.domain.Specification;

// public class CourseSpecification {

//     public static Specification<Course> hasName(String name) {
//         return (root, query, cb) ->
//             name == null ? null : cb.equal(root.get("name"), name);
//     }
    
//     public static Specification<Course> nameLike(String name) {
//         return (root, query, cb) ->
//             name == null ? null : cb.like(root.get("name"), name + "%");
//     }

//     public static Specification<Course> hasDescription(String description) {
//         return (root, query, cb) ->
//             description == null ? null : cb.equal(root.get("description"), description);
//     }
// }
package com.example.labwork2.specification;

import com.example.labwork2.model.Course;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecification {

    // Фильтр по точному совпадению поля name
    public static Specification<Course> hasName(String name) {
        return (root, query, cb) ->
            name == null ? null : cb.equal(root.get("name"), name);
    }
    
    // Фильтр по частичному совпадению поля name
    public static Specification<Course> nameLike(String name) {
        return (root, query, cb) ->
            name == null ? null : cb.like(root.get("name"), name + "%");
    }
    
    // Фильтр по полному совпадению description
    public static Specification<Course> hasDescription(String description) {
        return (root, query, cb) ->
            description == null ? null : cb.equal(root.get("description"), description);
    }
}
