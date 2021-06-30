package net.junhabaek.authservice.service.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CreateMemberRequest {
    @NotNull(message="Email can not be null")
    @Email
    private String email;

    @NotNull(message = "Password can not be null")
    @Size(min=6, message= "Password must be at least six characters long.")
    private String pwd;

    @NotNull(message = "Name can not be null")
    @Size(min=2, message = "Name must be at least six characters long.")
    private String name;

    private Date createdAt;
}
