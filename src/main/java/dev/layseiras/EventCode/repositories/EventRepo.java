package dev.layseiras.EventCode.repositories;

import dev.layseiras.EventCode.entities.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepo extends CrudRepository<Event, Long> {
    public Event findByPrettyName(String prettyName);
}
