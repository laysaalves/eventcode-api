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
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriptionController {
    @Autowired
    private SubscriptionService service;

    @PostMapping({"/subscription/{prettyName}", "/subscription/{prettyName}/{userId}"})
    public ResponseEntity<?> createSubscription(@PathVariable String prettyName, @RequestBody User subscriber, @PathVariable (required = false) Long userId) {
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
    @GetMapping("/subscription/{prettyName}/ranking")
    public ResponseEntity<?> generateRankingByEvent(@PathVariable String prettyName) {
        try{
            return ResponseEntity.ok(service.getCompleteRanking(prettyName).subList(0, 3));
        }catch(EventNotFoundException e){
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        }
    }
    @GetMapping("/subscription/{prettyName}/ranking/{userId}")
    public ResponseEntity<?> generateRankingByEventAndUser(@PathVariable String prettyName, @PathVariable Long userId){
        try {
            return ResponseEntity.ok(service.getRankingByUser(prettyName, userId));
        }catch(Exception ex){
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        }
    }
}
