package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.domain.BookAndAuthor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAndAuthorRepository bookAndAuthorRepository;

    @Test
    @Transactional
    void manyToManyTest(){
        Book book1 = givenBook("book1");
        Book book2 = givenBook("book2");
        Book book3 = givenBook("devbook1");
        Book book4 = givenBook("devbook2");

        Author author1 = givenAuthor("martin");
        Author author2 = givenAuthor("kelly");

        BookAndAuthor ba1 = givenBookAndAuthor(book1, author1);
        BookAndAuthor ba2 = givenBookAndAuthor(book2, author2);
        BookAndAuthor ba3 = givenBookAndAuthor(book3, author1);
        BookAndAuthor ba4 = givenBookAndAuthor(book3, author2); //여기선 중간 테이블은 1이기 때문에 두번 지정해야 함.
        BookAndAuthor ba5 = givenBookAndAuthor(book4, author1);
        BookAndAuthor ba6 = givenBookAndAuthor(book4, author2);


//        book1.addAuthor(author1);
//        book2.addAuthor(author2);
//        book3.addAuthor(author1, author2);
//        book4.addAuthor(author1, author2);
//
//        author1.addBook(book1, book3, book4);
//        author2.addBook(book2, book3, book4);
        book1.addBookAndAuthors(ba1);
        book2.addBookAndAuthors(ba2);
        book3.addBookAndAuthors(ba3, ba4);
        book4.addBookAndAuthors(ba5, ba6);

        author1.addBookAndAuthors(ba1, ba3, ba5);
        author2.addBookAndAuthors(ba2, ba4, ba6);
        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));

        bookRepository.findAll().get(2).getBookAndAuthors().forEach(o-> System.out.println(o.getAuthor()));
        authorRepository.findAll().get(0).getBookAndAuthors().forEach(o-> System.out.println(o.getBook()));
//        System.out.println("authors through book : " + bookRepository.findAll().get(2).getAuthors());
//        System.out.println("books through author: " + authorRepository.findAll().get(0).getBooks());
    }
    private Book givenBook(String name){
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }
    private Author givenAuthor(String name){
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

    private BookAndAuthor givenBookAndAuthor(Book book, Author author){
        BookAndAuthor bookAndAuthor = new BookAndAuthor();
        bookAndAuthor.setBook(book);
        bookAndAuthor.setAuthor(author);

        return bookAndAuthorRepository.save(bookAndAuthor);
    }
}