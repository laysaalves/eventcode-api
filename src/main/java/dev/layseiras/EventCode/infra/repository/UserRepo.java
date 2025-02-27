package dev.layseiras.EventCode.infra.repository;

import dev.layseiras.EventCode.core.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
