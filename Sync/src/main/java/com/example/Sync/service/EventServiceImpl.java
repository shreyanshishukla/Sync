package com.example.Sync.service;

import com.example.Sync.model.Event;
import com.example.Sync.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    EventRepository eventRepository;

    @Override
    public void save(Event event) {
            eventRepository.save(event);
    }

    @Override
    public void update(Event event) {
        eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void delete(int id) {
      eventRepository.deleteById(id);
    }
}
