// package com.example.labwork2.controller;

// import com.example.labwork2.model.Student;
// import com.example.labwork2.service.StudentService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/students")
// public class StudentController {

//     @Autowired
//     private StudentService studentService;

//     // Доступ для чтения (ADMIN и USER)
//     @GetMapping
//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     public List<Student> getAllStudents(){
//         return studentService.getAllStudents();
//     }

//     // Создание студента доступно только ADMIN
    // @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    // public Student createStudent(@RequestBody Student student){
    //     return studentService.createStudent(student);
    // }

//     // Обновление студента – только ADMIN
//     @PutMapping("/{id}")
//     @PreAuthorize("hasRole('ADMIN')")
//     public Student updateStudent(@PathVariable Long id, @RequestBody Student student){
//         return studentService.updateStudent(id, student);
//     }

//     // Удаление студента – только ADMIN
//     @DeleteMapping("/{id}")
//     @PreAuthorize("hasRole('ADMIN')")
//     public void deleteStudent(@PathVariable Long id){
//         studentService.deleteStudent(id);
//     }
// }

// package com.example.labwork2.controller;

// import com.example.labwork2.dto.StudentDTO;
// import com.example.labwork2.model.Student;
// import com.example.labwork2.service.StudentService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.*;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.*;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/students")
// public class StudentController {

//     @Autowired
//     private StudentService studentService;

//     // GET с пагинацией и сортировкой (доступ для ADMIN и USER)
//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     @GetMapping
//     public ResponseEntity<Map<String, Object>> getAllStudents(
//             @RequestParam(defaultValue = "0") int page,
//             @RequestParam(defaultValue = "10") int size,
//             // Пример: sort=firstName,asc или sort=lastName,desc
//             @RequestParam(defaultValue = "firstName,asc") String[] sort) {

//         // Определяем направление сортировки (asc или desc)
//         Sort.Direction direction = sort[1].equalsIgnoreCase("desc")
//                 ? Sort.Direction.DESC
//                 : Sort.Direction.ASC;
//         Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
//         Page<Student> studentPage = studentService.getAllStudents(pageable);

//         // Конвертируем сущности в DTO
//         List<StudentDTO> studentDTOs = studentPage.getContent()
//                 .stream()
//                 .map(this::convertToDTO)
//                 .collect(Collectors.toList());

//         // Формируем ответ согласно требуемой структуре
//         Map<String, Object> response = new HashMap<>();
//         response.put("content", studentDTOs);
//         response.put("page", studentPage.getNumber());
//         response.put("size", studentPage.getSize());
//         response.put("totalElements", studentPage.getTotalElements());
//         response.put("totalPages", studentPage.getTotalPages());
//         response.put("last", studentPage.isLast());

//         return ResponseEntity.ok(response);
//     }

//     // Пример конвертации сущности Student в StudentDTO
//     private StudentDTO convertToDTO(Student student) {
//         // Если требуется маппинг списка зачислений, его можно добавить здесь
//         return new StudentDTO(
//                 student.getId(),
//                 student.getFirstName(),
//                 student.getLastName(),
//                 student.getEmail(),
//                 null  // Здесь можно преобразовать список Enrollment в EnrollmentDTO
//         );
//     }

//     // Остальные методы (POST, PUT, DELETE) можно реализовать по аналогии
// }

// package com.example.labwork2.controller;

// import com.example.labwork2.dto.StudentDTO;
// import com.example.labwork2.model.Student;
// import com.example.labwork2.service.StudentService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.*;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.*;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/students")
// public class StudentController {

//     @Autowired
//     private StudentService studentService;

//     // GET: Получить всех студентов с пагинацией и сортировкой (ADMIN и USER)
//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     @GetMapping
//     public ResponseEntity<Map<String, Object>> getAllStudents(
//             @RequestParam(defaultValue = "0") int page,
//             @RequestParam(defaultValue = "10") int size,
//             // Пример: sort=firstName,asc или sort=lastName,desc
//             @RequestParam(defaultValue = "firstName,asc") String[] sort) {

//         Sort.Direction direction = sort[1].equalsIgnoreCase("desc")
//                 ? Sort.Direction.DESC
//                 : Sort.Direction.ASC;
//         Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
//         Page<Student> studentPage = studentService.getAllStudents(pageable);

//         List<StudentDTO> studentDTOs = studentPage.getContent()
//                 .stream()
//                 .map(this::convertToDTO)
//                 .collect(Collectors.toList());

//         Map<String, Object> response = new HashMap<>();
//         response.put("content", studentDTOs);
//         response.put("page", studentPage.getNumber());
//         response.put("size", studentPage.getSize());
//         response.put("totalElements", studentPage.getTotalElements());
//         response.put("totalPages", studentPage.getTotalPages());
//         response.put("last", studentPage.isLast());

//         return ResponseEntity.ok(response);
//     }

//     // GET: Получить студента по ID (ADMIN и USER)
//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     @GetMapping("/{id}")
//     public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
//         Optional<Student> optionalStudent = studentService.getStudentById(id);
//         if (optionalStudent.isPresent()) {
//             StudentDTO dto = convertToDTO(optionalStudent.get());
//             return ResponseEntity.ok(dto);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     // POST: Создать нового студента (только ADMIN)
//     @PreAuthorize("hasRole('ADMIN')")
//     @PostMapping
//     public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
//         Student student = convertToEntity(studentDTO);
//         Student created = studentService.createStudent(student);
//         StudentDTO createdDTO = convertToDTO(created);
//         return ResponseEntity.ok(createdDTO);
//     }

//     // PUT: Обновить студента (только ADMIN)
//     @PreAuthorize("hasRole('ADMIN')")
//     @PutMapping("/{id}")
//     public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
//         Student student = convertToEntity(studentDTO);
//         Student updated = studentService.updateStudent(id, student);
//         StudentDTO updatedDTO = convertToDTO(updated);
//         return ResponseEntity.ok(updatedDTO);
//     }

//     // DELETE: Удалить студента (только ADMIN)
//     @PreAuthorize("hasRole('ADMIN')")
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
//         studentService.deleteStudent(id);
//         return ResponseEntity.noContent().build();
//     }

//     // Метод конвертации сущности Student в DTO
//     private StudentDTO convertToDTO(Student student) {
//         // Простой пример; зачисления можно добавить при необходимости
//         return new StudentDTO(
//                 student.getId(),
//                 student.getFirstName(),
//                 student.getLastName(),
//                 student.getEmail()
//         );
//     }

//     // Метод конвертации DTO в сущность Student
//     private Student convertToEntity(StudentDTO dto) {
//         Student student = new Student();
//         student.setId(dto.getId());
//         student.setFirstName(dto.getFirstName());
//         student.setLastName(dto.getLastName());
//         student.setEmail(dto.getEmail());
//         // Преобразование списка зачислений, если требуется, можно добавить
//         return student;
//     }
// }
package com.example.labwork2.controller;

import com.example.labwork2.dto.StudentDTO;
import com.example.labwork2.model.Student;
import com.example.labwork2.service.StudentService;
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
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Существующий CRUD-эндпоинт
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName,asc") String[] sort) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentService.getAllStudents(pageable);

        List<StudentDTO> studentDTOs = studentPage.getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("content", studentDTOs);
        response.put("page", studentPage.getNumber());
        response.put("size", studentPage.getSize());
        response.put("totalElements", studentPage.getTotalElements());
        response.put("totalPages", studentPage.getTotalPages());
        response.put("last", studentPage.isLast());

        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student created = studentService.createStudent(student);
        return ResponseEntity.ok(convertToDTO(created));
    }

    // 4. PUT /students/{id}   — обновляем существующего студента
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student updated = studentService.updateStudent(id, student);
        return ResponseEntity.ok(convertToDTO(updated));
    }

    // 5. DELETE /students/{id}
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    

    // Новый эндпоинт для фильтрации
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/filter")
    public ResponseEntity<Map<String, Object>> filterStudents(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false, name = "firstName_like") String firstNameLike,
            @RequestParam(required = false, name = "lastName_like") String lastNameLike,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentService.filterStudents(firstName, lastName, email, firstNameLike, lastNameLike, pageable);

        List<StudentDTO> studentDTOs = studentPage.getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("content", studentDTOs);
        response.put("totalElements", studentPage.getTotalElements());
        response.put("totalPages", studentPage.getTotalPages());

        // Добавляем применённые фильтры в ответ
        Map<String, Object> filters = new HashMap<>();
        if (firstName != null) filters.put("firstName", firstName);
        if (lastName != null) filters.put("lastName", lastName);
        if (email != null) filters.put("email", email);
        if (firstNameLike != null) filters.put("firstName_like", firstNameLike);
        if (lastNameLike != null) filters.put("lastName_like", lastNameLike);
        response.put("filtersApplied", filters);

        return ResponseEntity.ok(response);
    }

    // Преобразование сущности в DTO
    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
    
    // Преобразование DTO в сущность (если необходимо для POST/PUT)
    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        return student;
    }
}
