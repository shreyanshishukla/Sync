package com.example.Sync.controller;

import com.example.Sync.model.Event;
import com.example.Sync.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping("/getAllEvents")
    public List<Event> getEvents(){
       return eventService.getAllEvents();
    }

    @PostMapping(value = "/add",consumes = "application/json")
    public void save(@RequestBody Event event){
        eventService.save(event);
    }
    @PutMapping("/update")
    public void update(@RequestBody Event event){
        eventService.update(event);
    }
    @DeleteMapping("/delete/{id}")
    public void getEvents(@PathVariable int id){
        eventService.delete(id);
    }
}
