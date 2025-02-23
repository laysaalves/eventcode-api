package dev.layseiras.EventCode.core.service;

import dev.layseiras.EventCode.core.entities.Event;
import dev.layseiras.EventCode.core.entities.Subscription;
import dev.layseiras.EventCode.core.entities.User;
import dev.layseiras.EventCode.infra.dtos.SubscriptionResponse;
import dev.layseiras.EventCode.infra.exception.EventNotFoundException;
import dev.layseiras.EventCode.infra.exception.SubscriptionConflictException;
import dev.layseiras.EventCode.infra.exception.UserIndicadorNotFoundException;
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

    public SubscriptionResponse addNewSubscription(String eventName, User user, Long userId) {
        Subscription subs = new Subscription();
        Event evt = evtRepo.findByPrettyName(eventName);
        if (evt == null) {
            throw new EventNotFoundException("Event " + eventName + " not found");
        }

        User userRemembered = userRepo.findByEmail(user.getEmail());
        if (userRemembered == null) {
            userRemembered = userRepo.save(user);
        }

        User indicador = userRepo.findById(userId).orElse(null);
        if (indicador == null) {
            throw new UserIndicadorNotFoundException("User indicador " + userId + " not found");
        }
        subs.setEvent(evt);
        subs.setSubscriber(userRemembered);
        subs.setIndication(indicador);

        Subscription doubleSub = subRepo.findByEventAndSubscriber(evt, userRemembered);
        if (doubleSub != null) {
            throw new SubscriptionConflictException("Subscription in the " + eventName + " for user " + userRemembered.getUserName() + " already exists!");
        }

        Subscription res = subRepo.save(subs);
        return new SubscriptionResponse(res.getSubscriptionId(), "https://eventcode.com/subscription/" + res.getEvent().getPrettyName() + "/" + res.getSubscriber().getUserId());
    }
}