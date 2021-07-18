package net.junhabaek.authservice.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

public class SecurityUser extends User {

    public SecurityUser(String email, String encryptedPassword){
        //TODO 권한 설정
//        super(email, encryptedPassword, List.of(new SimpleGrantedAuthority("ROLE_GENERAL")));

        //정보 파기 같은 설정
//        super(email, encryptedPassword, true, true, true, true, new ArrayList<>());

        super(email, encryptedPassword, new ArrayList<>());
    }
}
