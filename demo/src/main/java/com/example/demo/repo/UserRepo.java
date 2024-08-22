package com.example.demo.repo;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserRepo  extends JpaRepository<User, Long> {

    Map<String, User> users = new HashMap<>();

    public default boolean isUserExists(String email) {
        return users.containsKey(email);
    }

    public default void createUser(String email, String name, String password) {
        // Assuming User class exists with constructor taking email, name, and password
        User newUser = new User(email, name, password);
        users.put(email, newUser);
    }
    User findByEmail(String email);




    @Query(value ="SELECT userID , name , email  FROM users ", nativeQuery = true )
    List<User> findAllWithoutPassword();

    User getUserById(Long userID);
}
