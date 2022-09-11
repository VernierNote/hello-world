package com.java.springboot.task.service;

import com.java.springboot.task.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Optional<Event> findById(Long id);

    List<Event> list();

    Page<Event> list(Integer from, Integer pageSize, String sortBy, Sort.Direction ordering);

    List<Event> findByName(String name);

    Event create(Event event);

    Event update(Event event);

    void delete(Long id);
}
