package net.junhabaek.authservice.service;

import net.junhabaek.authservice.domain.User;
import net.junhabaek.authservice.service.dto.CreateMemberRequest;

public interface UserService {
    public User createUser(CreateMemberRequest cmr);
}
