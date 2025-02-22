package dev.layseiras.EventCode.core.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subs_number")
    private String subscription_id;

    @ManyToOne
    @JoinColumn(name = "subs_user_id")
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "indication_user_id", nullable = true)
    private User indication;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Subscription() {
    }

    public Subscription(String subscription_id, User subscriber, User indication, Event event) {
        this.subscription_id = subscription_id;
        this.subscriber = subscriber;
        this.indication = indication;
        this.event = event;
    }

    public String getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public User getIndication() {
        return indication;
    }

    public void setIndication(User indication) {
        this.indication = indication;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
