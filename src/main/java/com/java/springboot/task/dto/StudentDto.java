package com.java.springboot.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private Long id;
    private String email;
    private Integer age;
    private String name;
    private EventDto event;
}