package dev.layseiras.EventCode.infra.presentation;

import dev.layseiras.EventCode.core.entities.User;
import dev.layseiras.EventCode.core.service.SubscriptionService;
import dev.layseiras.EventCode.infra.dtos.ErrorMessage;
import dev.layseiras.EventCode.infra.dtos.SubscriptionResponse;
import dev.layseiras.EventCode.infra.exception.EventNotFoundException;
import dev.layseiras.EventCode.infra.exception.SubscriptionConflictException;
import dev.layseiras.EventCode.infra.exception.UserIndicadorNotFoundException;
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

    @PostMapping({"/subscription/{prettyName}", "/subscription/{prettyName}/{userId}"})
    public ResponseEntity<?> createSubscription(@PathVariable String prettyName, @RequestBody User subscriber, @PathVariable Long userId) {
        try {
            SubscriptionResponse res = service.addNewSubscription(prettyName, subscriber, userId);
            if (res != null) {
                return ResponseEntity.ok(res);
            }
        } catch (EventNotFoundException | UserIndicadorNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        } catch (SubscriptionConflictException e) {
            return ResponseEntity.status(409).body(new ErrorMessage(e.getMessage()));
        }
        return ResponseEntity.badRequest().build();
    }
}
