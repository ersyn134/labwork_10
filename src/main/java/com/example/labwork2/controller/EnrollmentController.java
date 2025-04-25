package com.example.labwork2.controller;

import com.example.labwork2.dto.EnrollmentDTO;
import com.example.labwork2.dto.CourseDTO;
import com.example.labwork2.dto.StudentDTO;
import com.example.labwork2.model.Enrollment;
import com.example.labwork2.model.Course;
import com.example.labwork2.model.Student;
import com.example.labwork2.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEnrollments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Enrollment> enrollmentPage = enrollmentService.getAllEnrollments(pageable);

        List<EnrollmentDTO> enrollmentDTOs = enrollmentPage.getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("content", enrollmentDTOs);
        response.put("page", enrollmentPage.getNumber());
        response.put("size", enrollmentPage.getSize());
        response.put("totalElements", enrollmentPage.getTotalElements());
        response.put("totalPages", enrollmentPage.getTotalPages());
        response.put("last", enrollmentPage.isLast());

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/filter")
    public ResponseEntity<Map<String, Object>> filterEnrollments(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Enrollment> enrollmentPage = enrollmentService.filterEnrollments(studentId, courseId, pageable);

        List<EnrollmentDTO> enrollmentDTOs = enrollmentPage.getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("content", enrollmentDTOs);
        response.put("totalElements", enrollmentPage.getTotalElements());
        response.put("totalPages", enrollmentPage.getTotalPages());

        Map<String, Object> filters = new HashMap<>();
        if (studentId != null) filters.put("studentId", studentId);
        if (courseId != null) filters.put("courseId", courseId);
        response.put("filtersApplied", filters);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping
    public ResponseEntity<EnrollmentDTO> createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = convertToEntity(enrollmentDTO);
        Enrollment createdEnrollment = enrollmentService.createEnrollment(enrollment);
        EnrollmentDTO createdDTO = convertToDTO(createdEnrollment);
        return ResponseEntity.ok(createdDTO);
    }

    private EnrollmentDTO convertToDTO(Enrollment enrollment) {
        Course course = enrollment.getCourse();
        Student student = enrollment.getStudent();

        CourseDTO courseDTO = new CourseDTO(course.getId(), course.getName(), course.getDescription());
        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getEmail());

        return new EnrollmentDTO(enrollment.getId(), courseDTO, studentDTO);
    }

    private Enrollment convertToEntity(EnrollmentDTO dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(dto.getId());

        if (dto.getCourse() != null) {
            Course course = new Course();
            course.setId(dto.getCourse().getId());
            enrollment.setCourse(course);
        }
        if (dto.getStudent() != null) {
            Student student = new Student();
            student.setId(dto.getStudent().getId());
            enrollment.setStudent(student);
        }
        return enrollment;
    }
}
