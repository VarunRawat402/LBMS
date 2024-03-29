package com.example.demo.controllers;

import com.example.demo.exceptions.TxnException;
import com.example.demo.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TxnController {


    @Autowired
    TxnService ts;

    //To Issue a Book from the Library
    //Need to pass Student ID and Book ID
    @PostMapping("/txn/issue")
    public String issueTxn(@RequestParam("studentId") int studentId,@RequestParam("bookId") int bookId) throws TxnException {
        return ts.issueTxn(studentId,bookId);
    }

    //To Return the Book to the Library
    //Need to pass Student ID and Book ID
    @PostMapping("/txn/return")
    public String returnTxn(@RequestParam("studentId") int studentId,@RequestParam("bookId") int bookId) throws TxnException {
        return ts.returnTxn(studentId,bookId);
    }


}
