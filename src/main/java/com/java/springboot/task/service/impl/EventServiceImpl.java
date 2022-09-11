package com.java.springboot.task.service.impl;

import com.java.springboot.task.entity.Event;
import com.java.springboot.task.repository.EventRepository;
import com.java.springboot.task.service.EventService;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service("facultyService")
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;

	@Autowired
	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public Optional<Event> findById(Long id) {
		return eventRepository.findById(id);
	}

	@Override
	public List<Event> list() {
		return IteratorUtils.toList( eventRepository.findAll().iterator() );
	}

	@Override
	public Page<Event> list(Integer from, Integer pageSize, String sortBy, Sort.Direction ordering) {
		return eventRepository.findAll(
				PageRequest.of(from, pageSize,ordering, sortBy)
		);
	}

	@Override
	public List<Event> findByName(String name) {
		return eventRepository.findByName( name );
	}

	@Override
	@Transactional
	public Event create(Event event) {
		return eventRepository.save(event);
	}

	@Override
	@Transactional
	public Event update(Event event) {
		return eventRepository.save(event);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		eventRepository.deleteById(id);
	}
}
