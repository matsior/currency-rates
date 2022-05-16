package com.fourk.kursywalut4k.domain.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String password;
}