package dev.layseiras.EventCode.repositories;

import dev.layseiras.EventCode.entities.Event;
import dev.layseiras.EventCode.entities.Subscription;
import dev.layseiras.EventCode.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepo extends CrudRepository<Subscription, Long> {
    Subscription findByEventAndSubscriber(Event evt, User user);
}
