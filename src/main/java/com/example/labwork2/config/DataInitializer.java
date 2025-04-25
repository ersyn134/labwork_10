// package com.example.labwork2.config;

// import com.example.labwork2.model.*;
// import com.example.labwork2.repository.*;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.Optional;

// @Component
// public class DataInitializer implements CommandLineRunner {

//     private final UserRepository userRepository;
//     private final RoleRepository roleRepository;
//     private final CourseRepository courseRepository;
//     private final StudentRepository studentRepository;
//     private final EnrollmentRepository enrollmentRepository;
//     private final PasswordEncoder passwordEncoder;

//     public DataInitializer(UserRepository userRepository, RoleRepository roleRepository,
//                            CourseRepository courseRepository, StudentRepository studentRepository,
//                            EnrollmentRepository enrollmentRepository, PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.roleRepository = roleRepository;
//         this.courseRepository = courseRepository;
//         this.studentRepository = studentRepository;
//         this.enrollmentRepository = enrollmentRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @Override
//     @Transactional
//     public void run(String... args) {
//         System.out.println("=== Полная очистка базы данных ===");

//         // Удаление данных во всех таблицах в правильном порядке
//         enrollmentRepository.deleteAllInBatch();
//         studentRepository.deleteAllInBatch();
//         courseRepository.deleteAllInBatch();
//         userRepository.deleteAllInBatch();
//         roleRepository.deleteAllInBatch();

//         System.out.println("База данных полностью очищена!");

//         // === Добавление ролей ===
//         Role adminRole = new Role("ROLE_ADMIN");
//         Role teacherRole = new Role("ROLE_TEACHER");
//         Role studentRole = new Role("ROLE_STUDENT");
//         roleRepository.saveAll(List.of(adminRole, teacherRole, studentRole));
//         System.out.println("Роли добавлены!");

//         // === Добавление пользователей ===
//         User admin = new User("admin", passwordEncoder.encode("admin123"), true, List.of(adminRole));
//         User teacher = new User("teacher1", passwordEncoder.encode("teacher123"), true, List.of(teacherRole));
//         User student = new User("student1", passwordEncoder.encode("student123"), true, List.of(studentRole));
//         userRepository.saveAll(List.of(admin, teacher, student));
//         System.out.println("Пользователи добавлены!");

//         // === Добавление курсов ===
//         Course math = new Course(null, "Mathematics", "Basic Math Course", null);
//         Course physics = new Course(null, "Physics", "Basic Physics Course", null);
//         courseRepository.saveAll(List.of(math, physics));
//         System.out.println("Курсы добавлены!");

//         // === Добавление студентов ===
//         Student s1 = new Student(null, "Alice", "Johnson", "alice@example.com", null);
//         Student s2 = new Student(null, "Bob", "Smith", "bob@example.com", null);
//         Student s3 = new Student(null, "Charlie", "Brown", "charlie@example.com", null);
//         studentRepository.saveAll(List.of(s1, s2, s3));
//         System.out.println("Студенты добавлены!");

//         // === Запись студентов на курсы ===
//         Enrollment e1 = new Enrollment(null, s1, math);
//         Enrollment e2 = new Enrollment(null, s2, physics);
//         Enrollment e3 = new Enrollment(null, s3, math);
//         enrollmentRepository.saveAll(List.of(e1, e2, e3));
//         System.out.println("Студенты записаны на курсы!");

//         System.out.println("=== Инициализация данных завершена! ===");
//     }
// }
