package net.junhabaek.authservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.junhabaek.authservice.common.exception.StateInvalidException;
import net.junhabaek.authservice.domain.User;
import net.junhabaek.authservice.service.UserService;
import net.junhabaek.authservice.service.dto.LoginUserRequest;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userservice;
    private final Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userservice, Environment env) {
        super(authenticationManager);
        this.userservice = userservice;
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, StateInvalidException {

        try {
            LoginUserRequest loginUserRequest = new ObjectMapper().readValue(request.getInputStream(),
                    LoginUserRequest.class);

            // with username, password, roles
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(loginUserRequest.getEmail(), loginUserRequest.getPassword(),
                            new ArrayList<>());

            // return authenticated token
            return getAuthenticationManager().authenticate(token);

        } catch (IOException e) {
            throw new StateInvalidException("Wrong Login Format");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String email = ((SecurityUser)authResult.getPrincipal()).getUsername();

        User user = userservice.getUserByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException(email);
        }

        String token = Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        response.addHeader("token", token);
    }
}
