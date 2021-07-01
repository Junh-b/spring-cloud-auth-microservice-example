package net.junhabaek.authservice.web.rest;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.junhabaek.authservice.domain.User;
import net.junhabaek.authservice.service.UserService;
import net.junhabaek.authservice.service.dto.CreateUserRequest;
import net.junhabaek.authservice.web.rest.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("user_id") Long userId){
        User user = userService.findUserByUserId(userId);
        return ResponseEntity.ok(UserResponse.with(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(){
        Iterable<User> users = userService.findAllUser();
        List<UserResponse >responses = new ArrayList<>();
        users.forEach((user)->responses.add(UserResponse.with(user)));

        return ResponseEntity.ok(responses);
    }
}
