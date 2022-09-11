package com.java.springboot.task.controller;

import com.java.springboot.task.dto.EventDto;
import com.java.springboot.task.mapper.EventMapper;
import com.java.springboot.task.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/faculty")
public class RestEventController {

    private final EventService eventService;

    public RestEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("list")
    public Page<EventDto> showUpdateForm(@RequestParam ( name = "page", defaultValue = "0" ) Integer page,
                                         @RequestParam ( defaultValue = "5" ) Integer pageSize,
                                         @RequestParam ( defaultValue = "id" ) String sortBy,
                                         @RequestParam ( defaultValue = "ASC" ) Sort.Direction order ) {
        return EventMapper.MAPPER.mapCollectionToDto(eventService.list(page, pageSize, sortBy, order));
    }
}
