package com.example.readers_diary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Column(name = "rating", nullable = false)
    protected int rating;

    @Column(name = "content", nullable = false)
    protected String content;

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", content='" + content + '\'' +
                '}';
    }
}
