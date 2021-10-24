package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

//spring context를 로딩해서 테스트에 활용하겠다는 의미.
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    void crud(){ //create, read, update, delete
        userRepository.save(new User("david", "david@fastcampus.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@fastcampus.com"); //update

        userRepository.save(user);
    }

    @Test
    void select(){
        System.out.println(userRepository.findByName("martin"));

        System.out.println("findByEmail: "+ userRepository.findByEmail("martin@fastcampus.com"));
        System.out.println("getByEmail: "+ userRepository.getByEmail("martin@fastcampus.com"));
        System.out.println("readByEmail: "+ userRepository.readByEmail("martin@fastcampus.com"));
        System.out.println("queryByEmail: "+ userRepository.queryByEmail("martin@fastcampus.com"));
        System.out.println("findByCreatedAtAfter:"+ userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter:"+userRepository.findByIdAfter(4L));
    }

    @Test
    void pagingAndSortingTest(){
//        System.out.println("findTop1ByNameOrderByIdDesc:"+ userRepository.findTop1ByNameOrderByIdDesc("martin"));
        System.out.println("findByNameWithPaging:"+ userRepository.findByName("martin", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent());
    }

    @Test
    void insertAndUpdateTest(){
        User user = new User();
        user.setName("martin");
        user.setEmail("martin2@fastcampus.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrtin");

        userRepository.save(user2);
    }

    @Test
    void enumTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
//        user.setGender(Gender.MALE);

        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void userHistoryTest(){
        User user = new User();
        user.setEmail("martin-new@fastcampus.com");
        user.setName("martin-new");

        userRepository.save(user);

        user.setName("martin-new-new");
        userRepository.save(user);
        userHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void userRelationTest(){
        User user = new User();
        user.setName("David");
        user.setEmail("D@fastcampus.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
//        List<UserHistory> result = userHistoryRepository.findByUserId(
//                userRepository.findByEmail("D@fastcampus.com").getId());
//        result.forEach(System.out::println);

        List<UserHistory> result = userRepository.findByEmail("D@fastcampus.com").getUserHistories();
        result.forEach(System.out::println);
        System.out.println("UserHistory.getUser(): " +userHistoryRepository.findAll().get(0).getUser());
    }
}