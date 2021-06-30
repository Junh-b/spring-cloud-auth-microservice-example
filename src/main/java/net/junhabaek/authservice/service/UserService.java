package net.junhabaek.authservice.service;

import net.junhabaek.authservice.domain.User;
import net.junhabaek.authservice.service.dto.CreateUserRequest;

public interface UserService {
    public User createUser(CreateUserRequest cmr);
}
