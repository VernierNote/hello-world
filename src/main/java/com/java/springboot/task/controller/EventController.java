package com.java.springboot.task.controller;

import com.java.springboot.task.entity.Event;
import com.java.springboot.task.service.EventService;
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
@RequestMapping("/events/")
public class EventController {

    private static final String EVENT = "event";
    private static final String EVENTS = "events";

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute(EVENT, new Event());
        model.addAttribute(EVENT, eventService.list());
        return EVENTS;
    }

    @PostMapping("add")
    public String addEvent(@Valid Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(EVENT, event);
            model.addAttribute(EVENT, eventService.list());
            return EVENTS;
        } else {
            if (eventService.findByName(event.getName()).isEmpty()) {
                eventService.create(event);
            } else {
                String msg = String.format("Event with name %s already exists", event.getName());
                result.addError(new FieldError(EVENT, "name", event.getName(), false, null, null, msg));
                model.addAttribute(EVENT, event);
                model.addAttribute(EVENTS, eventService.list());
                return EVENTS;
            }
        }


        return "redirect:/events/list";
    }

    @GetMapping("delete/{id}")
    public String deleteEvent(@PathVariable("id") Long id, Model model) {
        eventService.delete(id);
        model.addAttribute(EVENTS, eventService.list());
        return "redirect:/events/list";
    }
}
