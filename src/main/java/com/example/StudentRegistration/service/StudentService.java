package com.example.StudentRegistration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentRegistration.entity.Student;
import com.example.StudentRegistration.repository.StudentRepository;
@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;
    public Student addStudent(Student student){
        return repo.save(student);
    }
    public List<Student> getAllStudents(){
        return repo.findAll();
    }
    public Optional<Student> getStudentById(Long id){
        return repo.findById(id);
    }

}
