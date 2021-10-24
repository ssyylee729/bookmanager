package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { //generic: Entity type, User의 PK type
    //아무것도 안적어도 기본적으로 JpaRepository에서 제공하는 다양한 method 활용 가능함.
    List<User> findByName(String name);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

//    User searchByEmail(String email);
//
//    User streamByEmail(String email);
//
//    User findUserByEmail(String email);
//
//    User findFirst1ByName(String name);
//
//    List<User> findByEmailAndName(String email, String name);
//
//    List<User> findByEmailOrName(String email, String name);

    List<User> findByCreatedAtAfter(LocalDateTime yesterday);

    List<User> findByIdAfter(Long id);

//    List<User> findByIdIsNotNull(Long id);

//    List<User> findTop1ByNameOrderByIdDesc(String name);

    Page<User> findByName(String name, Pageable pagable);

    @Query(value ="select * from user limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();

}
