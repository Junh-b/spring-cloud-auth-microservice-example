package net.junhabaek.authservice.service.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginUserRequest {
    @NotNull(message = "Email can not be null")
    @Email(message = "Please check valid email form")
    private String email;

    @NotNull(message = "Password can not be null")
    @Size(min = 4, message = "Password should be equal or longer than 4")
    private String password;
}
