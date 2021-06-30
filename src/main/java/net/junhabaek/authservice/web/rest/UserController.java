package net.junhabaek.authservice.web.rest;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.junhabaek.authservice.domain.User;
import net.junhabaek.authservice.service.UserService;
import net.junhabaek.authservice.service.dto.CreateUserRequest;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest cmr){
        User user = userService.createUser(cmr);

        CreateUserResponse response = new CreateUserResponse(user.getName(), user.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Data
    protected static class CreateUserResponse{
        private String name;
        private String email;

        public CreateUserResponse(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
}
