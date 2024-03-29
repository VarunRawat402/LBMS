package com.example.demo.Repository;

import com.example.demo.models.Book;
import com.example.demo.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    //Functions for getting the books using filter
    List<Book> findByName(String name);
    List<Book> findByAuthor_Name(String name);
    List<Book> findByGenre(Genre genre);


}
