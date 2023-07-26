package com.example.readers_diary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "books")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected int id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "author", nullable = false)
    protected String author;

    @Column(name = "creation_date", nullable = false)
    protected LocalDate creationDate = LocalDate.now();

    @Column(name = "completion_date")
    protected LocalDate completionDate;

    @Column(name = "isRead", nullable = false)
    protected boolean isRead;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "books_marks",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "marks_id", referencedColumnName = "id"))
    protected List<Mark> marks = new ArrayList<>();

    @Embedded
    @Column(name = "review")
    @AttributeOverrides({
            @AttributeOverride(name = "rating", column = @Column(name = "review_rating")),
            @AttributeOverride(name = "content", column = @Column(name = "review_content"))
    })
    protected Review review;

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public boolean deleteMark(Mark mark) {
        return marks.remove(mark);
    }

    public boolean addMark(Mark mark) {
        return marks.add(mark);
    }

    public Review deleteReview() {
        return this.review = null;
    }

    public void changeState() {
        this.isRead = !this.isRead;
        if (this.isRead) {
            this.completionDate = LocalDate.now();
        } else this.completionDate = null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", isRead=" + isRead +
                ", marks=" + marks +
                ", review=" + review.toString() +
                '}';
    }
}
