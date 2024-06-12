package org.example.jpastudent.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import org.example.jpastudent.model.Student;

import java.util.List;
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void findJohn1() {
        List<Student> lst = studentRepository.findAllByName("John1");
        assertEquals(0, lst.size());
    }
}