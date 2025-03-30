package com.pateltanmay.connectwise.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pateltanmay.connectwise.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
     Optional<User> findByEmail(String email);
     Optional<User> findByEmailAndPassword(String email, String password);
}
