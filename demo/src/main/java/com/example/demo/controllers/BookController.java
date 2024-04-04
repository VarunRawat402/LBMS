package com.example.demo.controllers;

import com.example.demo.enums.BookFilter;
import com.example.demo.models.Book;
import com.example.demo.requests.BookRequest;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bs;

    //To create a Book in the DB
    //Need to pass BookRequest and Author Details in the postman
    @PostMapping("/admin/book")
    public String createBook(@RequestBody @Valid BookRequest bookRequest){
        bs.create(bookRequest);
        return "The book has been successfully added to the library.";
    }
    
    //To get the List of Books based on filter
    //Need to pass filter and the value of the filter
    //Ex - Genre --> Romance
    @GetMapping("/book/filter")
    public List<Book> getBookByFilter(@RequestParam("filter") BookFilter bookFilter, @RequestParam("value") String value){
        return bs.find(bookFilter,value);
    }

    //To get the details of the book by ID
    @GetMapping("/book")
    public Book getBookById(@RequestParam("bookId") int bId){
        return bs.findBookById(bId);
    }

    @DeleteMapping("/admin/book")
    public String deleteBook(@RequestParam("id") int id){
        bs.delete(id);
        return "The book with ID " + id + " has been successfully removed from the library.";
    }

}
