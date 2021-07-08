package com.dwi.mongo;

import com.dwi.mongo.models.Address;
import com.dwi.mongo.models.Gender;
import com.dwi.mongo.models.Student;
import com.dwi.mongo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate template) {
        return args -> {

            Address address = new Address(
                    "England",
                    "London",
                    "NE9"
            );

            Student student = new Student(
                    "Jamila",
                    "Ahmed",
                    "jahmed@gmail.com",
                    Gender.FEMALE,
                    address,
                    List.of("Computer Science", "Maths"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );

//            usingMongoTemplateAndQuery(repository, template, student);

            repository.findStudentByEmail(student.getEmail())
                    .ifPresentOrElse(s -> {
                        System.out.println(s + "  already exists");
                    }, () -> {
                        System.out.println("inserting student : " + student);
                        repository.insert(student);
                    });

        };
    }

    private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate template, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(student.getEmail()));
        List<Student> students = template.find(query, Student.class);


        if (students.size() > 1) {
            throw new IllegalStateException("Found many students with email :" + student.getEmail());
        }

        if (students.isEmpty()) {
            System.out.println("inserting student : " + student);
            repository.insert(student);
        } else {
            System.out.println(student + "  already exists");
        }
    }


}
