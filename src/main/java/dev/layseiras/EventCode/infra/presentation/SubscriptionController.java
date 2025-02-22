package dev.layseiras.EventCode.infra.presentation;

import dev.layseiras.EventCode.core.entities.User;
import dev.layseiras.EventCode.core.service.SubscriptionService;
import dev.layseiras.EventCode.infra.dtos.ErrorMessage;
import dev.layseiras.EventCode.infra.dtos.SubscriptionResponse;
import dev.layseiras.EventCode.infra.exception.EventNotFoundException;
import dev.layseiras.EventCode.infra.exception.SubscriptionConflictException;
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
            SubscriptionResponse res = service.addNewSubscription(prettyName, subscriber);
            if (res != null) {
                return ResponseEntity.ok(res);
            }
        } catch (EventNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        } catch (SubscriptionConflictException s) {
            return ResponseEntity.status(409).body(new ErrorMessage(s.getMessage()));
        }
        return ResponseEntity.badRequest().build();
    }
}
