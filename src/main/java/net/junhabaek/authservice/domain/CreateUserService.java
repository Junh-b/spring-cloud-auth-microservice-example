package net.junhabaek.authservice.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserService {
    private final PasswordEncoder passwordEncoder;

    public User createUser(String email, String name, String pw){
        return new User(email, name, encryptPw(pw));
    }

    protected String encryptPw(String pw){
        return passwordEncoder.encode(pw);
    }
}
