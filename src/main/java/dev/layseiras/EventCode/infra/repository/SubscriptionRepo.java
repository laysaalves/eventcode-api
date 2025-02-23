package dev.layseiras.EventCode.infra.repository;

import dev.layseiras.EventCode.core.entities.Event;
import dev.layseiras.EventCode.core.entities.Subscription;
import dev.layseiras.EventCode.core.entities.User;
import dev.layseiras.EventCode.infra.dtos.SubscriptionRankingElement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepo extends CrudRepository<Subscription, Long> {
    Subscription findByEventAndSubscriber(Event evt, User user);

    @Query(value = "SELECT COUNT(subs_number) AS quantity, " +
            "indication_user_id, username " +
            "FROM tb_subscription " +
            "INNER JOIN tb_user ON tb_subscription.indication_user_id = tb_user.user_id " +
            "WHERE indication_user_id IS NOT NULL AND event_id = :eventId " +
            "GROUP BY indication_user_id " +
            "ORDER BY quantity DESC", nativeQuery = true)
    List<SubscriptionRankingElement> generateRanking(@Param("eventId") Long eventId);

}
