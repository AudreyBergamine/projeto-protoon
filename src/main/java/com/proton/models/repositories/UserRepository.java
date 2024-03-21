package com.proton.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proton.models.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByUsername(String username);
  Optional<User> findByUsernameAndPassword(String username, String password);
}
