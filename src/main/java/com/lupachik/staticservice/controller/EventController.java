package com.lupachik.staticservice.controller;

import com.lupachik.staticservice.model.Event;
import com.lupachik.staticservice.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("/events")
    public String findAll(
            @RequestParam(required = false, defaultValue = "") String filter,
                          Model model){
        List<Event> events = service.findByType_Title(filter);
//        List<Event> events = service.findAll();
        model.addAttribute("events", events);
//        model.addAttribute("filter", filter);

        return "event-list";
    }

//    https://www.baeldung.com/spring-data-jpa-pagination-sorting

    @GetMapping("/pagination")
    public String listEvents(Model model,
//                             @RequestParam(value = "sise", required = false, defaultValue = "0") Integer sise,
                             @RequestParam(value = "page", required = false, defaultValue = "0")Integer page){

//        Page<Event> pageEvents = service.findAllPage(PageRequest.of(page, size));
//        Page<Event> pageEvents = service.findAllPage(PageRequest.of(page, size, Sort.Direction.DESC,"date"));
//        Pageable sortedByDateDescNameAsc = PageRequest.of(page, size, Sort.by("date").descending().and(Sort.by("name")));
        Page<Event> pageEvents = service.findAllPage(PageRequest.of(page, 4, Sort.by("date").descending().and(Sort.by("name"))));
        model.addAttribute("eventsPage", pageEvents);
        model.addAttribute("numbers", IntStream.range(0, pageEvents.getTotalPages()).toArray());

        return "event-listpag";
    }

    @GetMapping("/filter")
    public String listFilterEvents(Model model,
                                   @RequestParam(required = false, defaultValue = "") String filter,
                                   @RequestParam(value = "page", required = false, defaultValue = "0")Integer page){
        Page<Event> pageEvents = service.
                findAllPageFilter(filter, PageRequest.of(page, 4));
        model.addAttribute("eventsPage", pageEvents);
        model.addAttribute("filter", filter);
        model.addAttribute("numbers", IntStream.range(0, pageEvents.getTotalPages()).toArray());

        return "event-listpagfil";
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
