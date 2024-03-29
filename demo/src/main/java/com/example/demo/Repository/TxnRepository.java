package com.example.demo.Repository;

import com.example.demo.enums.TransactionType;
import com.example.demo.models.Book;
import com.example.demo.models.Student;
import com.example.demo.models.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn,Integer>{

     Txn findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(
            Book book, Student student, TransactionType transactionType);

}
