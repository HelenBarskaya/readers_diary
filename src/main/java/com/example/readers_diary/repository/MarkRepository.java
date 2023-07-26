package com.example.readers_diary.repository;

import com.example.readers_diary.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
}
