package com.example.case_module_4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Cart cart;
}
