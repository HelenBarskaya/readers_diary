package com.example.readers_diary.controller;

import com.example.readers_diary.model.Book;
import com.example.readers_diary.model.Mark;
import com.example.readers_diary.service.BookService;
import com.example.readers_diary.service.MarkService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarkController {

    MarkService markService;
    BookService bookService;

    public MarkController(MarkService markService, BookService bookService) {
        this.markService = markService;
        this.bookService = bookService;
    }

    @GetMapping(path = "/marks")
    public List<Mark> getAllMarks() {
        return markService.getMarks();
    }

    @GetMapping(path = "/mark/{id}")
    public Mark getMarkById(@PathVariable int id) throws Exception {
        return markService.getMarkById(id);
    }

    @PostMapping(path = "/mark", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mark createMark(@RequestBody Mark mark) {
        return markService.saveMark(mark);
    }

    @PutMapping(path = "/mark", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mark updateMark(@RequestBody Mark mark) {
        return markService.saveMark(mark);
    }

    @DeleteMapping(path = "/mark/{id}")
    public void deleteMark(@PathVariable int id) throws Exception {
        List<Book> books = bookService.getBookByMarkId(id);
        markService.deleteMark(id, books);
    }
}
