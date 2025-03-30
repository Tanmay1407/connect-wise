package com.pateltanmay.connectwise.services;

import java.util.List;
import java.util.Optional;

import com.pateltanmay.connectwise.entities.User;

public interface UserService {
    User saveUser(User newUser);
    Optional<User> getUserById(String id);
    User getUserByEmail(String email);
    Optional<User> updateUser(User updatedUser);
    void deleteUser(String id);
    boolean isUserExists(String id);
    boolean isUserExistsByEmail(String email);
    List<User> getAllUsers();
}
