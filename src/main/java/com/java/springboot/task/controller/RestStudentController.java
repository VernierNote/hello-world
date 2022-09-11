package com.java.springboot.task.controller;

import com.java.springboot.task.dto.StudentDto;
import com.java.springboot.task.mapper.StudentMapper;
import com.java.springboot.task.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/student")
public class RestStudentController {

    private final StudentService studentService;

    public RestStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("list")
    public Page<StudentDto> showUpdateForm(@RequestParam ( name = "page", defaultValue = "0" ) Integer page,
                                           @RequestParam ( defaultValue = "5" ) Integer pageSize,
                                           @RequestParam ( defaultValue = "id" ) String sortBy,
                                           @RequestParam ( defaultValue = "ASC" ) Sort.Direction order ) {
        return StudentMapper.MAPPER.mapCollectionToDto(studentService.list(page, pageSize, sortBy, order));
    }
}
