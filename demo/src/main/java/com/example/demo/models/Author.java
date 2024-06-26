package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String country;
    private int age;

    @Column(unique = true, nullable = false)
    private String email;

    //Getting all the books written by this author
    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    private List<Book> bookList;

}
