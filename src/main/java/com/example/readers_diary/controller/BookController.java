package com.example.readers_diary.controller;

import com.example.readers_diary.model.Book;
import com.example.readers_diary.model.Review;
import com.example.readers_diary.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getBooksByMarkId(@RequestBody List<Integer> id) {
        return bookService.getBookByMarkId(id);
    }

    @GetMapping(path = "/book/{id}")
    public Book getBookById(@PathVariable int id) throws Exception {
        return bookService.getBookById(id);
    }

    @PostMapping(path = "/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping(path = "/{id}/review", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addReviewByBookId(@PathVariable int id, @RequestBody Review review) throws Exception {
        bookService.addReviewByBookId(id, review);
    }

    @PostMapping(path = "/book/{idBook}/{idMark}")
    public Book addMarkByBookId(@PathVariable int idBook, @PathVariable int idMark) throws Exception {
        return bookService.addMark(idBook, idMark);
    }

    @PutMapping(path = "/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping(path = "/{id}/review", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Review updateReviewByBookId(@PathVariable int id, @RequestBody Review review) throws Exception {
        bookService.addReviewByBookId(id, review);
        return review;
    }

    @PutMapping(path = "/{id}/state")
    public Book updateStateByBookId(@PathVariable int id) throws Exception {
        return bookService.changeStateById(id);
    }

    @DeleteMapping(path = "/book/{id}")
    public void deleteBookById(@PathVariable int id) throws Exception {
        bookService.deleteBook(id);
    }

    @DeleteMapping(path = "/{id}/review")
    public void deleteReviewByBookId(@PathVariable int id) throws Exception {
        bookService.deleteReview(id);
    }

    @DeleteMapping(path = "/book/{idBook}/{idMark}")
    public void deleteMarkByBookId(@PathVariable int idBook, @PathVariable int idMark) throws Exception {
        bookService.deleteMark(idBook, idMark);
    }

}
