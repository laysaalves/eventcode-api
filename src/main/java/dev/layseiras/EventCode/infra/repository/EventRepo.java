package dev.layseiras.EventCode.infra.repository;

import dev.layseiras.EventCode.core.entities.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepo extends CrudRepository<Event, Long> {
    public Event findByPrettyName(String prettyName);
}
