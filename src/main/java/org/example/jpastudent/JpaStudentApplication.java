package org.example.jpastudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class JpaStudentApplication {

    private static final Logger logger = LoggerFactory.getLogger(JpaStudentApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(JpaStudentApplication.class, args);
        logger.info("Application started");
    }

}
