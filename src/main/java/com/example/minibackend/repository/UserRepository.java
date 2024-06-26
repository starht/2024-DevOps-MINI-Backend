package com.example.minibackend.repository;

import com.example.minibackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUserId(String userId);
  boolean existsByUserId(String userId);
  boolean existsByName(String name);
  List<User> findAllByUserId(String userId);
}
