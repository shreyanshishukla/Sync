package com.example.Sync.service;

import com.example.Sync.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EventService {
    public void save(Event event);
    public  void update(Event event);
    public List<Event> getAllEvents();
    public void delete(int id);
}
