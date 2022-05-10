package com.fourk.kursywalut4k.domain.api;

import com.fourk.kursywalut4k.domain.user.User;
import com.fourk.kursywalut4k.domain.user.UserDao;

public class UserService {
    private UserDao userDao = new UserDao();

    public void register(UserRegistration userRegistration) {
        String userName = userRegistration.getUsername();
        String userEmail = userRegistration.getEmail();
        String userPassword = userRegistration.getPassword();
        User userToSave = new User(userName, userEmail, userPassword);
        userDao.save(userToSave);
    }
}
