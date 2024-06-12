package org.example.jpastudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.jpastudent.model.Student;
import org.example.jpastudent.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Optional;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

@RestController
public class StudentRestController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/")
    public String index() {
        return "Hello from Spring Boot";
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // Ikke en rigtig måde at gøre det på
    @GetMapping("/addstudent")
    public List<Student> addstudent() {
        Student std = new Student();
        std.setBornDate(LocalDate.now());
        std.setBornTime(LocalTime.now());

        List<Student> lst = studentRepository.findAll();
        std.setName("John" + lst.size() + 1);
        studentRepository.save(std);
        return studentRepository.findAll();
    }

    @GetMapping("/students/{name}")
    public List<Student> getAllStudentsByName(@PathVariable String name) {
        return studentRepository.findAllByName(name);
    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@RequestBody Student student) {
        System.out.println("Student: " + student);
        return studentRepository.save(student);
    }

    @PutMapping("/studentx")
    public Student putStudent(@RequestBody Student student) {
        Optional<Student> student1 = studentRepository.findById(student.getId());
        if (student1.isPresent()) {
            return studentRepository.save(student);
        } else {
            Student notStudent = new Student();
            notStudent.setName("Not found");
            return notStudent;
        }
    }

    @PutMapping("/student")
    public ResponseEntity<Student> putStudent2(@RequestBody Student student) {
        Optional<Student> student1 = studentRepository.findById(student.getId());
        if (student1.isPresent()) {
            return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> putStudent3(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> student1 = studentRepository.findById(id);
        if (student1.isPresent()) {
            return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Ekstra endpoint til anden opgave
    @GetMapping("/hello")
    public String doHello() {
        return "Hello";
    }
}
