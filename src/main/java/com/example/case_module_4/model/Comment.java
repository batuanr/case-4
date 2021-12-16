package com.example.case_module_4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    private Book book;
    @ManyToOne
    private User user;
}
