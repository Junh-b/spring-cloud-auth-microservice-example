package net.junhabaek.authservice.web.rest.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateMemberRequest {
    private String email;
    private String pwd;
    private String name;
    private Date createdAt;
}
