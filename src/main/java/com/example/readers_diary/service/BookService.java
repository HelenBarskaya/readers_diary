package com.example.readers_diary.service;

import com.example.readers_diary.model.Book;
import com.example.readers_diary.model.Mark;
import com.example.readers_diary.model.Review;
import com.example.readers_diary.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    public static final String ID_NOT_EXIST_EXCEPTION = "Book with '%s' id is not found";

    BookRepository bookRepository;
    MarkService markService;

    public BookService(BookRepository bookRepository, MarkService markService) {
        this.bookRepository = bookRepository;
        this.markService = markService;
    }

    public Book getBookById(int id) throws Exception {
        return bookRepository.findById(id)
                .orElseThrow(() -> exceptionProvider(id));
    }

    public List<Book> getBookByMarkId(List<Integer> id) {
        return bookRepository.getBooksByIdMark(id);
    }

    public List<Book> getBookByMarkId(int id) {
        return bookRepository.getBooksByIdMark(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(int id) throws Exception {
        deleteBook(getBookById(id));
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public void deleteMark(int idBook, int idMark) throws Exception {
        Book book = getBookById(idBook);
        Mark mark = markService.getMarkById(idMark);

        book.deleteMark(mark);
        bookRepository.save(book);
    }

    public void addReviewByBookId(int id, Review review) throws Exception {
        Book book = getBookById(id);
        book.setReview(review);
        bookRepository.save(book);
    }

    public void deleteReview(int id) throws Exception {
        Book book = getBookById(id);
        book.deleteReview();
        bookRepository.save(book);
    }

    public Book addMark(int idBook, int idMark) throws Exception {
        Book book = getBookById(idBook);
        Mark mark = markService.getMarkById(idMark);
        book.addMark(mark);
        return bookRepository.save(book);
    }

    public Book changeStateById(int id) throws Exception {
        Book book = getBookById(id);
        book.changeState();
        return bookRepository.save(book);
    }

    protected Exception exceptionProvider(int id) {
        String message = String.format(ID_NOT_EXIST_EXCEPTION, id);

        return new IllegalArgumentException(message);
    }
}
