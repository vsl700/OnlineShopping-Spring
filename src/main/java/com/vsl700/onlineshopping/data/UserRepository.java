package com.vsl700.onlineshopping.data;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vsl700.onlineshopping.data.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
