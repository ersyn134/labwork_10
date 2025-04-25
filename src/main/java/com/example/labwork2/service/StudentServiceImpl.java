// package com.example.labwork2.service;

// import com.example.labwork2.model.Student;
// import com.example.labwork2.repository.StudentRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// public class StudentServiceImpl implements StudentService {

//     @Autowired
//     private StudentRepository studentRepository;

//     public Optional<Student> getStudentById(Long id) {
//         return studentRepository.findById(id);
//     }
    
//     @Override
//     public Page<Student> getAllStudents(Pageable pageable) {
//         return studentRepository.findAll(pageable);
//     }
    
//     @Override
//     public Student createStudent(Student student) {
//         return studentRepository.save(student);
//     }
    
//     @Override
//     public Student updateStudent(Long id, Student student) {
//         Optional<Student> optional = studentRepository.findById(id);
//         if(optional.isPresent()){
//             Student existing = optional.get();
//             existing.setFirstName(student.getFirstName());
//             existing.setLastName(student.getLastName());
//             existing.setEmail(student.getEmail());
//             // Обновление зачислений можно добавить при необходимости
//             return studentRepository.save(existing);
//         } else {
//             throw new RuntimeException("Student not found");
//         }
//     }
    
//     @Override
//     public void deleteStudent(Long id) {
//         studentRepository.deleteById(id);
//     }
// }
package com.example.labwork2.service;

import com.example.labwork2.model.Student;
import com.example.labwork2.repository.StudentRepository;
import com.example.labwork2.specification.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
    
    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
    
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    
    @Override
    public Student updateStudent(Long id, Student student) {
        Optional<Student> optional = studentRepository.findById(id);
        if(optional.isPresent()){
            Student existing = optional.get();
            existing.setFirstName(student.getFirstName());
            existing.setLastName(student.getLastName());
            existing.setEmail(student.getEmail());
            // Обновление зачислений можно добавить при необходимости
            return studentRepository.save(existing);
        } else {
            throw new RuntimeException("Student not found");
        }
    }
    
    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    // Реализация динамической фильтрации
    @Override
    public Page<Student> filterStudents(String firstName, String lastName, String email,
                                        String firstNameLike, String lastNameLike,
                                        Pageable pageable) {
        Specification<Student> spec = Specification.where(null);
        
        if (firstName != null) {
            spec = spec.and(StudentSpecification.hasFirstName(firstName));
        }
        if (lastName != null) {
            spec = spec.and(StudentSpecification.hasLastName(lastName));
        }
        if (email != null) {
            spec = spec.and(StudentSpecification.hasEmail(email));
        }
        if (firstNameLike != null) {
            spec = spec.and(StudentSpecification.firstNameLike(firstNameLike));
        }
        if (lastNameLike != null) {
            spec = spec.and(StudentSpecification.lastNameLike(lastNameLike));
        }
        
        return studentRepository.findAll(spec, pageable);
    }
}
