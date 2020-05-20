package com.lupachik.staticservice.controller;

import com.lupachik.staticservice.model.Event;
import com.lupachik.staticservice.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("/events")
    public String findAll(@RequestParam(required = false, defaultValue = "") String filter,
                          Model model){
        List<Event> events = service.findByType(filter);
        model.addAttribute("events", events);
        model.addAttribute("filter", filter);
        return "event-list";
    }

    @GetMapping("/event-create")
    public String createEventForm(Event event){
        return "event-create";
    }

    @PostMapping("/event-create")
    public String createEvent(Event event){
        service.saveEvent(event);
        return "redirect:/events";
    }

    @GetMapping("event-delete/{id}")
    public String deleteEvent(@PathVariable("id") Integer id){
        service.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/event-update/{id}")
    public String updateEventForm(@PathVariable("id") Integer id, Model model){
        Event event = service.findById(id);
        model.addAttribute("event", event);
        return "event-update";
    }

    @PostMapping("/event-update")
    public String updateEvent(Event event){
        service.saveEvent(event);
        return "redirect:/events";
    }
}
