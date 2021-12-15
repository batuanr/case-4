package com.example.case_module_4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date borrowDate;
    private Date repaymentDate;
    @OneToOne
    private User user;
    @ManyToMany(mappedBy = "cartList")
    private Set<Book> bookList;
}
