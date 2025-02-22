package dev.layseiras.EventCode.services;

import dev.layseiras.EventCode.entities.Event;
import dev.layseiras.EventCode.entities.Subscription;
import dev.layseiras.EventCode.entities.User;
import dev.layseiras.EventCode.repositories.EventRepo;
import dev.layseiras.EventCode.repositories.SubscriptionRepo;
import dev.layseiras.EventCode.repositories.UserRepo;
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

    public Subscription addNewSubscription(String eventName, User user){
        Subscription subs = new Subscription();
        Event evt = evtRepo.findByPrettyName(eventName);

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
