package com.dwi.mongo.controllers;

import com.dwi.mongo.models.Student;
import com.dwi.mongo.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }


}
