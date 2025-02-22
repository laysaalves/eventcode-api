package dev.layseiras.EventCode.core.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subs_number")
    private Long subscriptionId;

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

    public Subscription(Long subscriptionId, User subscriber, User indication, Event event) {
        this.subscriptionId = subscriptionId;
        this.subscriber = subscriber;
        this.indication = indication;
        this.event = event;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
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
