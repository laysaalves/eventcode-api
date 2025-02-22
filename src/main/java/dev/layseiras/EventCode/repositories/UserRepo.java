package dev.layseiras.EventCode.repositories;

import dev.layseiras.EventCode.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
