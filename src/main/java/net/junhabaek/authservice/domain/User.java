package net.junhabaek.authservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private String encrytedPwd;

    public User(String email, String name, String encrytedPwd) {
        this.email = email;
        this.name = name;
        this.encrytedPwd = encrytedPwd;
    }
}
