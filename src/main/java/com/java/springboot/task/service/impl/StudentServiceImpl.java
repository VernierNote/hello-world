package com.java.springboot.task.service.impl;

import com.java.springboot.task.entity.Student;
import com.java.springboot.task.repository.StudentRepository;
import com.java.springboot.task.service.StudentService;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("studentService")
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> list() {
        return IteratorUtils.toList(studentRepository.findAll().iterator());
    }

    @Override
    public Page<Student> list(Integer from, Integer pageSize, String sortBy, Sort.Direction ordering) {
        return studentRepository.findAll(
            PageRequest.of(from, pageSize,ordering, sortBy)
        );
    }

    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    @Transactional
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> retrieveAllWithAgeMoreThan(Integer age) {

        List<Student> studentList = (List<Student>) studentRepository.findAll();

        List<Student> filteredStudents = new ArrayList<>();

        for (Student student : studentList) {
            if (student.getAge() >= age) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }
}
