package com.example.students.service;

import com.example.students.entities.Student;
import com.example.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * @author Андрей Борисов
 */
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Получения списка студентов.
     *
     * @return список студентов
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Вставка нового студета.
     *
     * @param student новый студент
     */
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    /**
     * Получение студента по id
     *
     * @param id ID студента
     * @return студент
     */
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    /**
     * Удаление студета по id.
     *
     * @param id уникальный идентификатор студента
     */
    public void removeStudentById(Integer id) {
        studentRepository.deleteById(id);
    }
}
