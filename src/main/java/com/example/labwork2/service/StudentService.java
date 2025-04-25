// package com.example.labwork2.service;

// import com.example.labwork2.model.Student;
// import com.example.labwork2.repository.StudentRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class StudentService {
//     @Autowired
//     private StudentRepository studentRepository;

//     public List<Student> getAllStudents(){
//         return studentRepository.findAll();
//     }

//     public Optional<Student> getStudentById(Long id){
//         return studentRepository.findById(id);
//     }

//     public Student createStudent(Student student){
//         return studentRepository.save(student);
//     }

//     public Student updateStudent(Long id, Student student){
//         student.setId(id);
//         return studentRepository.save(student);
//     }

//     public void deleteStudent(Long id){
//         studentRepository.deleteById(id);
//     }
// }

package com.example.labwork2.service;

import com.example.labwork2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface StudentService {
    Optional<Student> getStudentById(Long id);
    Page<Student> getAllStudents(Pageable pageable);
    Student createStudent(Student student);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);

    // Новый метод для динамической фильтрации
    Page<Student> filterStudents(String firstName, String lastName, String email,
                                 String firstNameLike, String lastNameLike,
                                 Pageable pageable);
}
