package com.darkube.server.repositories;

import java.util.Optional;

import com.darkube.server.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ '$or': [ { 'username': ?0 }, { 'email': ?1 } ] }")
    Optional<User> login(String username, String email);

}
