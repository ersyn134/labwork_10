package com.example.labwork2.specification;

import com.example.labwork2.model.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    // Фильтр по точному совпадению firstName
    public static Specification<Student> hasFirstName(String firstName) {
        return (root, query, cb) ->
            firstName == null ? null : cb.equal(root.get("firstName"), firstName);
    }

    // Фильтр по частичному совпадению firstName
    public static Specification<Student> firstNameLike(String firstName) {
        return (root, query, cb) ->
            firstName == null ? null : cb.like(root.get("firstName"), firstName + "%");
    }

    // Фильтр по точному совпадению lastName
    public static Specification<Student> hasLastName(String lastName) {
        return (root, query, cb) ->
            lastName == null ? null : cb.equal(root.get("lastName"), lastName);
    }

    // Фильтр по частичному совпадению lastName
    public static Specification<Student> lastNameLike(String lastName) {
        return (root, query, cb) ->
            lastName == null ? null : cb.like(root.get("lastName"), lastName + "%");
    }

    // Фильтр по email
    public static Specification<Student> hasEmail(String email) {
        return (root, query, cb) ->
            email == null ? null : cb.equal(root.get("email"), email);
    }
}
