package com.fourk.kursywalut4k.domain.user;

import lombok.*;

@Getter
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
