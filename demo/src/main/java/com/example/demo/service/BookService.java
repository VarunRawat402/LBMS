package com.example.demo.service;

import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.enums.BookFilter;
import com.example.demo.enums.Genre;
import com.example.demo.requests.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository ar;

    @Autowired
    BookRepository br;

    //
    public void create(BookRequest bookRequest){
        Book book = bookRequest.to();

        Author author = book.getAuthor();
        Author authorDb = ar.findByEmail(author.getEmail());

        if(authorDb==null) {
            authorDb = ar.save(author);
        }

        book.setAuthor(authorDb);
        br.save(book);
    }

    public List<Book> find(BookFilter bookFilter, String value){

        switch (bookFilter){
            case NAME:
                return br.findByName(value);
            case AUTHOR_NAME:
                return br.findByAuthor_Name(value);
            case GENRE:
                return br.findByGenre(Genre.valueOf(value));
            default:
                return new ArrayList<>();
        }

    }

    public Book findBookById(int id){
        return br.findById(id).orElse(null);
    }

    public void create(Book book){
        br.save(book);
    }
}
