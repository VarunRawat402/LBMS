package com.example.demo.models;

import com.example.demo.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Txn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated
    private TransactionType transactionType;

    private String externalTxnId;

    private double cost;

    @CreationTimestamp
    private Date txnDate;

    //Foreign Key ( Book )
    @ManyToOne
    @JoinColumn
    private Book book;

    //Foreign key ( Student )
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Student student;


}
