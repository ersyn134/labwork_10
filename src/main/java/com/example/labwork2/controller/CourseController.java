// package com.example.labwork2.controller;

// import com.example.labwork2.model.Course;
// import com.example.labwork2.service.CourseService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/courses")
// public class CourseController {
//     @Autowired
//     private CourseService courseService;

//     // GET доступен ADMIN и USER
//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     @GetMapping
//     public List<Course> getAllCourses(){
//         return courseService.getAllCourses();
//     }

//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     @GetMapping("/{id}")
//     public Optional<Course> getCourseById(@PathVariable Long id){
//         return courseService.getCourseById(id);
//     }

//     // POST, PUT, DELETE доступны только для ADMIN
//     @PreAuthorize("hasRole('ADMIN')")
//     @PostMapping
//     public Course createCourse(@RequestBody Course course){
//         return courseService.createCourse(course);
//     }

//     @PreAuthorize("hasRole('ADMIN')")
//     @PutMapping("/{id}")
//     public Course updateCourse(@PathVariable Long id, @RequestBody Course course){
//         return courseService.updateCourse(id, course);
//     }

//     @PreAuthorize("hasRole('ADMIN')")
//     @DeleteMapping("/{id}")
//     public void deleteCourse(@PathVariable Long id){
//         courseService.deleteCourse(id);
//     }
// }

// package com.example.labwork2.controller;

// import com.example.labwork2.dto.CourseDTO;
// import com.example.labwork2.model.Course;
// import com.example.labwork2.service.CourseService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.*;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.*;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/courses")
// public class CourseController {

//     @Autowired
//     private CourseService courseService;

//     // GET с пагинацией и сортировкой (доступ для ADMIN и USER)
//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     @GetMapping
//     public ResponseEntity<Map<String, Object>> getAllCourses(
//             @RequestParam(defaultValue = "0") int page,
//             @RequestParam(defaultValue = "10") int size,
//             // Пример: sort=name,asc или sort=name,desc
//             @RequestParam(defaultValue = "name,asc") String[] sort) {

//         Sort.Direction direction = sort[1].equalsIgnoreCase("desc")
//                 ? Sort.Direction.DESC
//                 : Sort.Direction.ASC;
//         Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
//         Page<Course> coursePage = courseService.getAllCourses(pageable);

//         List<CourseDTO> courseDTOs = coursePage.getContent()
//                 .stream()
//                 .map(this::convertToDTO)
//                 .collect(Collectors.toList());

//         Map<String, Object> response = new HashMap<>();
//         response.put("content", courseDTOs);
//         response.put("page", coursePage.getNumber());
//         response.put("size", coursePage.getSize());
//         response.put("totalElements", coursePage.getTotalElements());
//         response.put("totalPages", coursePage.getTotalPages());
//         response.put("last", coursePage.isLast());

//         return ResponseEntity.ok(response);
//     }

//     private CourseDTO convertToDTO(Course course) {
//         return new CourseDTO(course.getId(), course.getName(), course.getDescription());
//     }

//     // Остальные методы (GET по id, POST, PUT, DELETE) можно реализовать по аналогии
// }

// package com.example.labwork2.controller;

// import com.example.labwork2.dto.CourseDTO;
// import com.example.labwork2.model.Course;
// import com.example.labwork2.service.CourseService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.*;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.*;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/courses")
// public class CourseController {

//     @Autowired
//     private CourseService courseService;

//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     @GetMapping
//     public ResponseEntity<Map<String, Object>> getAllCourses(
//             @RequestParam(defaultValue = "0") int page,
//             @RequestParam(defaultValue = "10") int size,
//             @RequestParam(defaultValue = "name,asc") String[] sort) {

//         Sort.Direction direction = sort[1].equalsIgnoreCase("desc")
//                 ? Sort.Direction.DESC
//                 : Sort.Direction.ASC;
//         Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
//         Page<Course> coursePage = courseService.getAllCourses(pageable);

//         List<CourseDTO> courseDTOs = coursePage.getContent()
//                 .stream()
//                 .map(this::convertToDTO)
//                 .collect(Collectors.toList());

//         Map<String, Object> response = new HashMap<>();
//         response.put("content", courseDTOs);
//         response.put("page", coursePage.getNumber());
//         response.put("size", coursePage.getSize());
//         response.put("totalElements", coursePage.getTotalElements());
//         response.put("totalPages", coursePage.getTotalPages());
//         response.put("last", coursePage.isLast());

//         return ResponseEntity.ok(response);
//     }

//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     @GetMapping("/{id}")
//     public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
//         Optional<Course> optionalCourse = courseService.getCourseById(id);
//         if (optionalCourse.isPresent()) {
//             CourseDTO dto = convertToDTO(optionalCourse.get());
//             return ResponseEntity.ok(dto);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @PreAuthorize("hasRole('ADMIN')")
//     @PostMapping
//     public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
//         Course course = convertToEntity(courseDTO);
//         Course created = courseService.createCourse(course);
//         CourseDTO createdDTO = convertToDTO(created);
//         return ResponseEntity.ok(createdDTO);
//     }

//     @PreAuthorize("hasRole('ADMIN')")
//     @PutMapping("/{id}")
//     public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
//         Course course = convertToEntity(courseDTO);
//         Course updated = courseService.updateCourse(id, course);
//         CourseDTO updatedDTO = convertToDTO(updated);
//         return ResponseEntity.ok(updatedDTO);
//     }

//     @PreAuthorize("hasRole('ADMIN')")
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
//         courseService.deleteCourse(id);
//         return ResponseEntity.noContent().build();
//     }

//     private CourseDTO convertToDTO(Course course) {
//         return new CourseDTO(course.getId(), course.getName(), course.getDescription());
//     }

//     private Course convertToEntity(CourseDTO dto) {
//         Course course = new Course();
//         course.setId(dto.getId());
//         course.setName(dto.getName());
//         course.setDescription(dto.getDescription());
//         return course;
//     }
// }
package com.example.labwork2.controller;

import com.example.labwork2.dto.CourseDTO;
import com.example.labwork2.model.Course;
import com.example.labwork2.service.CourseService;
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
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Существующий CRUD-эндпоинт (оставляем для совместимости)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = courseService.getAllCourses(pageable);

        List<CourseDTO> courseDTOs = coursePage.getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("content", courseDTOs);
        response.put("page", coursePage.getNumber());
        response.put("size", coursePage.getSize());
        response.put("totalElements", coursePage.getTotalElements());
        response.put("totalPages", coursePage.getTotalPages());
        response.put("last", coursePage.isLast());

        return ResponseEntity.ok(response);
    }
    // 3) POST /courses — создать новый курс
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO dto) {
        Course course = convertToEntity(dto);
        Course created = courseService.createCourse(course);
        return ResponseEntity.ok(convertToDTO(created));
    }

    // 4) PUT /courses/{id} — обновить курс
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseDTO dto) {
        Course course = convertToEntity(dto);
        Course updated = courseService.updateCourse(id, course);
        return ResponseEntity.ok(convertToDTO(updated));
    }

    // 5) DELETE /courses/{id} — удалить курс
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    // Новый эндпоинт для фильтрации
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/filter")
    public ResponseEntity<Map<String, Object>> filterCourses(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, name = "name_like") String nameLike,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = courseService.filterCourses(name, description, nameLike, pageable);

        List<CourseDTO> courseDTOs = coursePage.getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("content", courseDTOs);
        response.put("totalElements", coursePage.getTotalElements());
        response.put("totalPages", coursePage.getTotalPages());

        Map<String, Object> filters = new HashMap<>();
        if (name != null) filters.put("name", name);
        if (description != null) filters.put("description", description);
        if (nameLike != null) filters.put("name_like", nameLike);
        response.put("filtersApplied", filters);

        return ResponseEntity.ok(response);
    }

    private CourseDTO convertToDTO(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getDescription());
    }
    
    // Метод преобразования для POST/PUT, если требуется
    private Course convertToEntity(CourseDTO dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        return course;
    }
}

