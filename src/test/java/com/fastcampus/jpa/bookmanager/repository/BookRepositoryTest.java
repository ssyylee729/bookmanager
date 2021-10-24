package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.domain.Publisher;
import com.fastcampus.jpa.bookmanager.domain.Review;
import com.fastcampus.jpa.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("JPA 강의");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();

        User user = userRepository.findByEmail("martin@fastcampus.com");

        System.out.println("Review :" + user.getReviews());
        System.out.println("Book :" + user.getReviews().get(0).getBook());
        System.out.println("Publisher: " + user.getReviews().get(0).getBook().getPublisher());

    }

    private void givenBookAndReview(){
        givenReview(givenUser(), givenBook(givenPublisher()));
    }
    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("JPA 패키지");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }
    private User givenUser(){
        return userRepository.findByEmail("martin@fastcampus.com");
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("fastcampus");

        return publisherRepository.save(publisher);
    }
    private void givenReview(User user, Book book){
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("재밌어요");
        review.setScore(5.0f);
        review.setUser(user);;
        review.setBook(book);

        reviewRepository.save(review);
    }
}
