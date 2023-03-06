package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.APRIL;
import static java.time.Month.MAY;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student oussama = new Student(

                    "Oussama",
                    "oussama@gmai.com",
                    LocalDate.of(2000, APRIL, 5)
            );
            Student saad = new Student(

                    "Saad",
                    "saad@gmail.com ",
                    LocalDate.of(2002, MAY, 29)
            );

            repository.saveAll(
                    List.of(oussama, saad)
            );
        };
    }
}
