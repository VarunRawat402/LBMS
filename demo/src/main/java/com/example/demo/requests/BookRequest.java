package com.example.demo.requests;

import com.example.demo.enums.Genre;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookRequest {


    private String name;
    private Genre genre;
    private int cost;

    @NotNull
    private Author author;

    public Book to(){
        return Book.builder().name(name).genre(genre).cost(cost).author(author).build();
    }
}
