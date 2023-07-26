package com.example.readers_diary.service;

import com.example.readers_diary.model.Book;
import com.example.readers_diary.model.Mark;
import com.example.readers_diary.repository.MarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {

    public static final String ID_NOT_EXIST_EXCEPTION = "Mark with '%s' id is not found";

    MarkRepository markRepository;

    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public Mark saveMark(Mark mark) {
        return markRepository.save(mark);
    }

    public Mark getMarkById(int id) throws Exception {
        return markRepository.findById(id)
                .orElseThrow(() -> exceptionProvider(id));
    }

    public void deleteMark(int id, List<Book> books) throws Exception {
        Mark mark = markRepository.findById(id)
                .orElseThrow(() -> exceptionProvider(id));

        for (Book book : books) {
            book.deleteMark(mark);
        }
        markRepository.delete(mark);
    }

    public List<Mark> getMarks() {
        return markRepository.findAll();
    }

    protected Exception exceptionProvider(int id) {
        String message = String.format(ID_NOT_EXIST_EXCEPTION, id);

        return new IllegalArgumentException(message);
    }
}
