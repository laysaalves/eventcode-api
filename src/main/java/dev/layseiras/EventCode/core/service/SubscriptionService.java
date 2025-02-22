package dev.layseiras.EventCode.core.service;

import dev.layseiras.EventCode.core.entities.Event;
import dev.layseiras.EventCode.core.entities.Subscription;
import dev.layseiras.EventCode.core.entities.User;
import dev.layseiras.EventCode.infra.exception.EventNotFoundException;
import dev.layseiras.EventCode.infra.repository.EventRepo;
import dev.layseiras.EventCode.infra.repository.SubscriptionRepo;
import dev.layseiras.EventCode.infra.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private EventRepo evtRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SubscriptionRepo subRepo;

    public Subscription addNewSubscription(String eventName, User user) {
        Subscription subs = new Subscription();
        Event evt = evtRepo.findByPrettyName(eventName);
        if (evt == null) {
            throw new EventNotFoundException("Event " + eventName + " not found");
        }

        User userRemembered = userRepo.findByEmail(user.getEmail());
        if (userRemembered == null) {
            userRemembered = userRepo.save(user);
        }
        subs.setEvent(evt);
        subs.setSubscriber(userRemembered);

        Subscription res = subRepo.save(subs);
        return res;
    }
}
