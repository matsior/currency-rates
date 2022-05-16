package com.fourk.kursywalut4k.domain.api;

import com.fourk.kursywalut4k.domain.user.User;
import com.fourk.kursywalut4k.domain.user.UserDao;

import java.util.Optional;

public class UserService {
    private final UserDao userDao = new UserDao();
    private final UserMapper userMapper = new UserMapper();

    public void register(UserRegistration userRegistration) {
        User userToSave = userMapper.map(userRegistration);
        userDao.saveUser(userToSave);
    }

    public Optional<UserDto> findByName(String name) {
        return userDao.findByUsername(name)
                .map(userMapper::map);
    }

    private static class UserMapper{

        UserDto map(User user) {
            return new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword()
            );
        }

        User map(UserRegistration userRegistration) {
            return new User(
                    userRegistration.getUsername(),
                    userRegistration.getEmail(),
                    userRegistration.getPassword()
            );
        }
    }
}