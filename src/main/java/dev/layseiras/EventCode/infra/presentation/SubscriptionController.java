package dev.layseiras.EventCode.infra.presentation;

import dev.layseiras.EventCode.core.entities.Subscription;
import dev.layseiras.EventCode.core.entities.User;
import dev.layseiras.EventCode.core.service.SubscriptionService;
import dev.layseiras.EventCode.infra.exception.EventNotFoundException;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {
    @Autowired
    private SubscriptionService service;

    @PostMapping("/subscription/{prettyName}")
    public ResponseEntity<?> createSubscription(@PathVariable String prettyName, @RequestBody User subscriber) {
        try {
            Subscription res = service.addNewSubscription(prettyName, subscriber);
            if (res != null) {
                return ResponseEntity.ok(res);
            }
        } catch (EventNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        }
        return ResponseEntity.badRequest().build();
    }
}
