package net.junhabaek.authservice.service;

import lombok.RequiredArgsConstructor;
import net.junhabaek.authservice.common.exception.EntityNotFoundException;
import net.junhabaek.authservice.domain.CreateUserService;
import net.junhabaek.authservice.domain.User;
import net.junhabaek.authservice.repository.UserRepository;
import net.junhabaek.authservice.security.SecurityUser;
import net.junhabaek.authservice.service.dto.CreateUserRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final CreateUserService createUserService;

    @Override
    public User createUser(CreateUserRequest cmr) {

        User user = createUserService.createUser(cmr.getEmail(), cmr.getName(), cmr.getPwd());
        userRepository.save(user);

        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("entity not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException(email);
        }

        return new SecurityUser(user.getEmail(), user.getEncryptedPwd());
    }
}
