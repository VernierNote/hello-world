package com.java.springboot.task.service;


import com.java.springboot.task.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> findById(Long id);

    List<Student> list();

    Page<Student> list(Integer from, Integer pageSize, String sortBy, Sort.Direction ordering);

    List<Student> findByName(String name);

    Student create(Student student);

    Student update(Student student);

    void delete(Long id);
}
