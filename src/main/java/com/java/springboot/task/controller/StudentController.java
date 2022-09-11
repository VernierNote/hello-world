package com.java.springboot.task.controller;

import com.java.springboot.task.entity.Student;
import com.java.springboot.task.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/students/")
public class StudentController {

    private final StudentService studentService;

    private static final String STUDENT = "student";
    private static final String STUDENTS = "students";

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute(STUDENT, new Student());
        model.addAttribute(STUDENTS, studentService.list());
        return STUDENTS;
    }

    @PostMapping("add")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(STUDENT, student);
            model.addAttribute(STUDENTS, studentService.list());
            return STUDENTS;
        } else {
            if (studentService.findByName(student.getName()).isEmpty()) {
                studentService.create(student);
            } else {
                String msg = String.format("Student with name %s already exists", student.getName());
                result.addError(new FieldError(STUDENT, "name", student.getName(), false, null, null, msg));
                model.addAttribute(STUDENT, student);
                model.addAttribute(STUDENTS, studentService.list());
                return STUDENTS;
            }
        }


        return "redirect:/students/list";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        studentService.delete(id);
        model.addAttribute(STUDENTS, studentService.list());
        return "redirect:/students/list";
    }
}
