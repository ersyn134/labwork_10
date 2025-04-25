package com.example.labwork2.service;

import com.example.labwork2.model.Enrollment;
import com.example.labwork2.model.Student;
import com.example.labwork2.model.Course;
import com.example.labwork2.repository.EnrollmentRepository;
import com.example.labwork2.repository.StudentRepository;
import com.example.labwork2.repository.CourseRepository;
import com.example.labwork2.specification.EnrollmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private StudentRepository studentRepository;   // Добавлено для загрузки полного студента

    @Autowired
    private CourseRepository courseRepository;     // Добавлено для загрузки полного курса
    
    @Override
    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public Page<Enrollment> getAllEnrollments(Pageable pageable) {
        return enrollmentRepository.findAll(pageable);
    }

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        // 1. Сохраняем зачисление
        Enrollment saved = enrollmentRepository.save(enrollment);

        // 2. Загружаем полный объект студента
        Student student = studentRepository.findById(saved.getStudent().getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // 3. Загружаем полный объект курса
        Course course = courseRepository.findById(saved.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 4. Формируем письмо
        String studentName = student.getFirstName() + " " + student.getLastName();
        String courseTitle = course.getName();
        String date = saved.getEnrollmentDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

        String subject = "Enrollment Confirmation";
        String body = String.format(
                "Dear %s,%n" +
                "You have been successfully enrolled in the course \"%s\" starting from %s.%n" +
                "Best regards,",
                studentName, courseTitle, date
        );

        // 5. Отправляем email
        emailService.sendSimpleEmail(new String[]{student.getEmail()}, subject, body);

        return saved;
    }

    @Override
    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {
        Optional<Enrollment> optional = enrollmentRepository.findById(id);
        if (optional.isPresent()) {
            Enrollment existing = optional.get();
            existing.setStudent(enrollment.getStudent());
            existing.setCourse(enrollment.getCourse());
            return enrollmentRepository.save(existing);
        } else {
            throw new RuntimeException("Enrollment not found");
        }
    }

    @Override
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public Page<Enrollment> filterEnrollments(Long studentId, Long courseId, Pageable pageable) {
        Specification<Enrollment> spec = Specification.where(null);

        if (studentId != null) {
            spec = spec.and(EnrollmentSpecification.hasStudentId(studentId));
        }
        if (courseId != null) {
            spec = spec.and(EnrollmentSpecification.hasCourseId(courseId));
        }

        return enrollmentRepository.findAll(spec, pageable);
    }
}
