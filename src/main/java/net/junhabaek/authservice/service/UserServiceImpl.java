package net.junhabaek.authservice.service;

import lombok.RequiredArgsConstructor;
import net.junhabaek.authservice.domain.CreateUserService;
import net.junhabaek.authservice.domain.User;
import net.junhabaek.authservice.repository.UserRepository;
import net.junhabaek.authservice.service.dto.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final CreateUserService createUserService;

    @Override
    public User createUser(CreateUserRequest cmr) {

        User user = createUserService.createUser(cmr.getEmail(), cmr.getName(), cmr.getPwd());
        userRepository.save(user);

        return user;
    }
}
