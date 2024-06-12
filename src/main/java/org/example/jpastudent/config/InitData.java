package org.example.jpastudent.config;

import org.example.jpastudent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.example.jpastudent.model.Student;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class InitData implements CommandLineRunner{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        Student s1 = new Student();
        s1.setName("John1");
        s1.setBornDate(LocalDate.of(2000, 1, 1));
        s1.setBornTime(LocalTime.of(12, 0));
        studentRepository.save(s1);
        System.out.println("Student saved: " + s1);
    }
}
