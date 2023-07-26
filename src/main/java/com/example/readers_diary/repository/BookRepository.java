package com.example.readers_diary.repository;

import com.example.readers_diary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM BOOKS WHERE ID IN (SELECT BOOK_ID FROM BOOKS_MARKS WHERE MARKS_ID IN ?1)",
            nativeQuery = true)
    List<Book> getBooksByIdMark(List<Integer> id);

    @Query(value = "SELECT * FROM BOOKS WHERE ID IN (SELECT BOOK_ID FROM BOOKS_MARKS WHERE MARKS_ID=?1)",
            nativeQuery = true)
    List<Book> getBooksByIdMark(int id);
}
