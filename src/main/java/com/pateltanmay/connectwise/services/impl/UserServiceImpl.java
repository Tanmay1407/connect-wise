package com.pateltanmay.connectwise.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pateltanmay.connectwise.entities.User;
import com.pateltanmay.connectwise.helpers.AppConstants;
import com.pateltanmay.connectwise.helpers.ResourceNotFound;
import com.pateltanmay.connectwise.repositories.UserRepo;
import com.pateltanmay.connectwise.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void deleteUser(String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User Not Found"));
        userRepo.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public boolean isUserExists(String id) {
        User user = userRepo.findById(id).orElse(null);
        return user != null;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(null);
        return user != null;
    }

    @Override
    public User saveUser(User newUser) {
        String userId =  UUID.randomUUID().toString();
        newUser.setId(userId);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRoleList(List.of(AppConstants.ROLE_USER));
        return userRepo.save(newUser);
    }

    @Override
    public Optional<User> updateUser(User updatedUser) {
        User existingUser = userRepo.findById(updatedUser.getId()).orElseThrow(() -> new ResourceNotFound("User Not Found"));
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAbout(updatedUser.getAbout());
        User savedUser = userRepo.save(existingUser);
        return Optional.ofNullable(savedUser);
    }

    


}
