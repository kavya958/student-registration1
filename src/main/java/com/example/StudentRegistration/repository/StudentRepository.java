package com.example.StudentRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentRegistration.entity.Student;


public interface StudentRepository extends JpaRepository<Student,Long>{

}
