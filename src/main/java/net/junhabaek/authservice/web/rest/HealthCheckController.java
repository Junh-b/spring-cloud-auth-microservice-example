package net.junhabaek.authservice.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HealthCheckController {
    private final Environment env;

    @GetMapping("/health_check")
    public ResponseEntity<String> status(){
        return ResponseEntity.ok(String.format(env.getProperty("token.secret")));
    }
}
