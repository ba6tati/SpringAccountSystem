package com.ba6tati.account.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ba6tati.account.system.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    
    Optional<UserModel> findByUsernameAndPassword(String username, String password);
}
