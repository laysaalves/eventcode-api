package dev.layseiras.EventCode.core.service;

import dev.layseiras.EventCode.core.entities.Event;
import dev.layseiras.EventCode.infra.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepo eventRepository;

    public Event addNewEvent(Event event) {
        event.setPrettyName(event.getName().toLowerCase().replaceAll(" ", "-"));
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents(){
        return (List<Event>)eventRepository.findAll();
    }

    public Event getEventByPrettyName(String prettyName){
        return eventRepository.findByPrettyName(prettyName);
    }
}
