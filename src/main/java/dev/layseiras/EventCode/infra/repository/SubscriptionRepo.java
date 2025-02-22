package dev.layseiras.EventCode.infra.repository;

import dev.layseiras.EventCode.core.entities.Event;
import dev.layseiras.EventCode.core.entities.Subscription;
import dev.layseiras.EventCode.core.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepo extends CrudRepository<Subscription, Long> {
    Subscription findByEventAndSubscriber(Event evt, User user);
}
