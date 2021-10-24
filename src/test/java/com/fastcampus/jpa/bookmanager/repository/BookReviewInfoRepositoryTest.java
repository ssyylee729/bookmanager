package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class BookReviewInfoRepositoryTest {

    @Autowired
    BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void crudTest(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
//        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAvgReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> "+ bookReviewInfoRepository.findAll());
    }

//    @Test
//    void crudTest2(){
//
//        givenBook();
//        givenBookReviewInfo();
//
//        Book result =
//                bookReviewInfoRepository
//                        .findById(1L)
//                        .orElseThrow(RuntimeException::new)
//                        .getBook();
//
//
//        System.out.println(">>> " + result);
//        BookReviewInfo result2 = bookRepository
//                .findById(6L)
//                .orElseThrow(RuntimeException::new)
//                .getBookReviewInfo();
//
//        System.out.println(">>> " + result2);
//
//    }

    private Book givenBook(){
        Book book = new Book();
        book.setName("JPA");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);
        return bookRepository.save(book);
    }
    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAvgReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> "+ bookReviewInfoRepository.findAll());

    }
}