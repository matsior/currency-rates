package com.fourk.kursywalut4k.model.user;

import lombok.*;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private Integer id;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @Setter
    @NonNull
    private String password;
}