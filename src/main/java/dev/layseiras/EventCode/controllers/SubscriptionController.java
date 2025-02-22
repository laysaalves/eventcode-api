package dev.layseiras.EventCode.controllers;

import dev.layseiras.EventCode.entities.Subscription;
import dev.layseiras.EventCode.entities.User;
import dev.layseiras.EventCode.services.SubscriptionService;
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
    public ResponseEntity<Subscription> createSubscription(@PathVariable String prettyName, @RequestBody User subscriber) {
        Subscription res = service.addNewSubscription(prettyName, subscriber);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().build();
    }
}
