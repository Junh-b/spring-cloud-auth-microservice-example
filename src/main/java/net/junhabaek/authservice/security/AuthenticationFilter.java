package net.junhabaek.authservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.junhabaek.authservice.common.exception.StateInvalidException;
import net.junhabaek.authservice.service.dto.LoginUserRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
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
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
