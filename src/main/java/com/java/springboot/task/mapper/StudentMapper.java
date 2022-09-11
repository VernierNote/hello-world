package com.java.springboot.task.mapper;

import com.java.springboot.task.dto.StudentDto;
import com.java.springboot.task.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

@Mapper( uses = EventMapper.class )
public interface StudentMapper {
    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "age", source = "age"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "event", source = "event")
    })
    StudentDto mapToDto(Student source);

    default Page<StudentDto> mapCollectionToDto(
            Page<Student> source) {
        return new PageImpl(source.getContent().stream().map(this::mapToDto).collect(Collectors.toList()), source.getPageable(), source.getTotalElements());
    }

}