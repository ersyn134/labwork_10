package com.example.labwork2;

import com.example.labwork2.model.Student;
import com.example.labwork2.model.Course;
import com.example.labwork2.model.Enrollment;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static void main(String[] args) {
        Faker faker = new Faker();

        // Генерация студентов
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            // id генерируем вручную, т.к. обычно он создается БД
            student.setId((long) (i + 1));
            student.setFirstName(faker.name().firstName());
            student.setLastName(faker.name().lastName());
            student.setEmail(faker.internet().emailAddress());
            students.add(student);
        }

        // Генерация курсов
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Course course = new Course();
            course.setId((long) (i + 1));
            course.setName(faker.educator().course());
            course.setDescription(faker.lorem().sentence());
            courses.add(course);
        }

        // Генерация зачислений: свяжем случайных студентов и курсы
        List<Enrollment> enrollments = new ArrayList<>();
        int enrollmentId = 1;
        for (Student student : students) {
            // Каждый студент будет зачислен на 1-2 курса
            int enrollmentsCount = faker.number().numberBetween(1, 3);
            for (int i = 0; i < enrollmentsCount; i++) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId((long) enrollmentId++);
                // Выбираем случайный курс из списка
                Course course = courses.get(faker.number().numberBetween(0, courses.size()));
                enrollment.setStudent(student);
                enrollment.setCourse(course);
                enrollments.add(enrollment);
            }
        }

        // Привязка зачислений к студентам и курсам (опционально)
        for (Student student : students) {
            List<Enrollment> studentEnrollments = new ArrayList<>();
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getStudent().getId().equals(student.getId())) {
                    studentEnrollments.add(enrollment);
                }
            }
            student.setEnrollments(studentEnrollments);
        }

        for (Course course : courses) {
            List<Enrollment> courseEnrollments = new ArrayList<>();
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getCourse().getId().equals(course.getId())) {
                    courseEnrollments.add(enrollment);
                }
            }
            course.setEnrollments(courseEnrollments);
        }

        // Вывод сгенерированных данных
        System.out.println("Сгенерированные студенты:");
        students.forEach(s -> System.out.println(
                "ID: " + s.getId() +
                ", Имя: " + s.getFirstName() +
                " " + s.getLastName() +
                ", Email: " + s.getEmail()));

        System.out.println("\nСгенерированные курсы:");
        courses.forEach(c -> System.out.println(
                "ID: " + c.getId() +
                ", Название: " + c.getName() +
                ", Описание: " + c.getDescription()));

        System.out.println("\nСгенерированные зачисления:");
        enrollments.forEach(e -> System.out.println(
                "ID: " + e.getId() +
                ", Студент: " + e.getStudent().getFirstName() + " " + e.getStudent().getLastName() +
                ", Курс: " + e.getCourse().getName()));
    }
}
