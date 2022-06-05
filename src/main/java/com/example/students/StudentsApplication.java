package com.example.students;

import com.example.students.ui.ConsoleUserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class StudentsApplication {

    private final ConsoleUserInterface userInterface;

    public static void main(String[] args) {
        SpringApplication.run(StudentsApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        userInterface.createUserInterface();
    }

}
