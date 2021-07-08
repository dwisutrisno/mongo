package com.dwi.mongo.repositories;

import com.dwi.mongo.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {


    Optional<Student> findStudentByEmail(String email);

}
