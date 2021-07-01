package net.junhabaek.authservice.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import net.junhabaek.authservice.domain.User;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String email;
    private String name;
    private Long userId;
//    private List<ResponseOrder> orders;


    protected UserResponse() {
    }

    public static UserResponse with(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.email = user.getEmail();
        userResponse.name = user.getName();
        userResponse.userId = user.getId();

        return userResponse;
    }
}
