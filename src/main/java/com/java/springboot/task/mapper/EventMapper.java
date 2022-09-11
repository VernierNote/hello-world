package com.java.springboot.task.mapper;

import com.java.springboot.task.dto.EventDto;
import com.java.springboot.task.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

@Mapper
public interface EventMapper {

    EventMapper MAPPER = Mappers.getMapper(EventMapper.class);

        @Mappings({
            @Mapping(source = "event.id", target = "id"),
            @Mapping(source = "event.name", target = "name"),
    })
        EventDto mapToDto(Event event);

    default Page<EventDto> mapCollectionToDto(
            Page<Event> source) {
        return new PageImpl(source.getContent().stream().map(this::mapToDto).collect(Collectors.toList()), source.getPageable(), source.getTotalElements());
    }
}
