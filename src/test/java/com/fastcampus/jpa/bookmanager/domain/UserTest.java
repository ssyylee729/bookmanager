package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test(){
        User user = new User();
        user.setEmail("ssyylee@fastcampus.com");
        user.setName("ssyylee");

        System.out.println(">>> " + user);
//        User user1 = new User(null, "ssyylee", "s@n.com", LocalDateTime.now(), LocalDateTime.now());
        User user2 = new User("ssyylee", "s@n.com");
    }
}