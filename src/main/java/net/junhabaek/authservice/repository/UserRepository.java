package net.junhabaek.authservice.repository;

import net.junhabaek.authservice.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    public User findByEmail(String email);
}
