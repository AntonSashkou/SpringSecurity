package com.sashkou.storage;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> users = List.of(
            new User("admin@email.com", "$2a$12$N64qyYKkAK3JM8H9BDdP5uVGy0PKiAlnVlbi6GaKdeUPUIPt8VGb2", User.Role.ADMIN),
            new User("user@email.com", "$2a$12$BAEj3Aq0cNIju7Lb2rxoIus3UWs7LkuZct19bq0inOs9T5bHcuHoS", User.Role.USER)
    );

    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

}
