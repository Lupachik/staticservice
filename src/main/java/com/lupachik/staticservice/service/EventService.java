package com.lupachik.staticservice.service;

import com.lupachik.staticservice.model.Event;
import com.lupachik.staticservice.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }


    public Event findById(Integer id){
        return repository.getOne(id);
    }

    public List<Event> findAll(){
        return repository.findAll();
    }

    public Event saveEvent(Event event){
        return repository.save(event);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public List<Event> findByType(String type){
        if (type != null && !type.isEmpty()) {
            return repository.findAllByType(type);
        }else {
            return repository.findByOrderByDateDescNameAsc();
        }
    }

    public Page<Event> findAllPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Page<Event> findAllPageFilter(String type,Pageable pageable){
        if (type != null && !type.isEmpty()) {
            return repository.findByType_Title(type, pageable);
        }else {
            return repository.findByOrderByDateDescNameAsc(pageable);
        }
    }

    public List<Event> findByType_Title(String type){
        if (type != null && !type.isEmpty()) {
            return repository.findByType_Title(type);
        }else {
            return repository.findAll();
        }
    }
}
