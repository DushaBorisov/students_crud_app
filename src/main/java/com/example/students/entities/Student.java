package com.example.students.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String patronymic;

    @Column(name = "student_group")
    private Integer studentGroup;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", studentGroup=" + studentGroup +
                ", dateOfBirth=" + dateOfBirth;
    }
}
