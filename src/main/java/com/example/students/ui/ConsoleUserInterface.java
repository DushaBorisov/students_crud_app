package com.example.students.ui;

import com.example.students.entities.Student;
import com.example.students.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
@Service
/*
 * Класс консольного пользовательского интерфейса.
 * @author Андрей Борисов
 */
public class ConsoleUserInterface {

    private final StudentService studentService;

    public void createUserInterface() {

        while (true) {
            System.out.println("Выберите операцию: \n" +
                    "--------------------------------------- \n" +
                    "1) Вывести список всех студентов \n" +
                    "2) Добавить нового студента \n" +
                    "3) Удалить студента \n" +
                    "4) Завершить программу \n" +
                    "--------------------------------------- \n"
            );

            Scanner in = new Scanner(System.in);
            int num = in.nextInt();

            switch (num) {
                case 1:
                    getStudentList();
                    break;
                case 2:
                    addNewStudent();
                    break;

                case 3:
                    removeStudentById();
                    break;
                case 4:
                    return;
            }
        }
    }

    /**
     * Выводит список студентов в консоль.
     */
    private void getStudentList() {
        List<Student> studentList = studentService.getAllStudents();
        System.out.println("Список студентов: ");
        studentList.forEach(System.out::println);
    }

    /**
     * Добавляет нового студента в базу.
     */
    private void addNewStudent() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите имя студента: ");
        String name = in.nextLine();

        System.out.println("Введите фамилию студента: ");
        String surname = in.nextLine();

        System.out.println("Введите отчество студента: ");
        String patronymic = in.nextLine();

        Integer group = null;
        boolean correctInputGroupFlag = false;

        while (!correctInputGroupFlag) {

            System.out.println("Введите группу студента студента (число): ");
            try {
                group = in.nextInt();
                in.nextLine();
                correctInputGroupFlag = true;
            } catch (Exception ex) {
                in.nextLine();
                System.out.println("Неверный формат группы.");
            }
        }


        boolean correctInputDateOfBirthFlag = false;
        Date dateOfBir = null;

        while (!correctInputDateOfBirthFlag) {

            System.out.println("Введите дату рождения студента (формат: yyyy-MM-dd ): ");
            String dateOfBirth = in.nextLine();

            try {
                dateOfBir = Date.valueOf(dateOfBirth);
                correctInputDateOfBirthFlag = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный формат даты.");
            }
        }

        Student newStudent = Student.builder()
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .studentGroup(group)
                .dateOfBirth(dateOfBir)
                .build();

        studentService.addStudent(newStudent);
    }

    /**
     * Удаление студента по id
     */
    private void removeStudentById() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите ID студента: ");
        Integer studentId = in.nextInt();

        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isEmpty()) {
            System.out.println(String.format("Студент с ID = %s не найден в базе.", studentId));
            return;
        }

        studentService.removeStudentById(studentId);
    }


}
