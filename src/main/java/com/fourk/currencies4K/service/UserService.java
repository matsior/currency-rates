package com.fourk.currencies4K.service;

import com.fourk.currencies4K.dto.UserDto;
import com.fourk.currencies4K.dto.UserRegistration;
import com.fourk.currencies4K.model.user.User;
import com.fourk.currencies4K.dao.UserDao;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private final UserDao userDao = new UserDao();
    private final UserMapper userMapper = new UserMapper();

    public void register(UserRegistration userRegistration) {
        User userToSave = userMapper.map(userRegistration);
        hashPasswordWithSha256(userToSave);
        userDao.saveUser(userToSave);
    }

    public Optional<UserDto> findByName(String name) {
        return userDao.findByUsername(name)
                .map(userMapper::map);
    }

    public List<UserDto> findAll() {
        return userDao.findAll()
                .stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    private void hashPasswordWithSha256(User user) {
        String sha256Password = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(sha256Password);
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