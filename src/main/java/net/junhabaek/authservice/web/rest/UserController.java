package net.junhabaek.authservice.web.rest;

import lombok.RequiredArgsConstructor;
import net.junhabaek.authservice.domain.User;
import net.junhabaek.authservice.service.UserService;
import net.junhabaek.authservice.web.rest.dto.CreateMemberRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateMemberRequest cmr){
        User user = userService.createUser(cmr);
        return ResponseEntity.ok(user.getId().toString());
    }
}
