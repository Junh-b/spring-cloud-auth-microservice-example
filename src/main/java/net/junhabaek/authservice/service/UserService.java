package net.junhabaek.authservice.service;

import net.junhabaek.authservice.domain.User;
import net.junhabaek.authservice.service.dto.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(CreateUserRequest cmr);
    User findUserByUserId(Long userId);
    Iterable<User> findAllUser();
}
