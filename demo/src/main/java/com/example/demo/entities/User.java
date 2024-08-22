package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @JsonProperty
    @Column
    private String name;

    @JsonProperty
    @Column
    private String email;

    @JsonProperty
    @Column
    private String password;


    public User(String email, String name, String password) {
    }

    public Long getId() {
        return userID;
    }

    public class UserDetailsResponse {
        private String name;
        private Long userID;
        private String email;

        public UserDetailsResponse() {
        }

        public UserDetailsResponse(String name, Long userID, String email) {
            this.name = name;
            this.userID = userID;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getUserID() {
            return userID;
        }

        public void setUserID(Long userID) {
            this.userID = userID;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }



}
