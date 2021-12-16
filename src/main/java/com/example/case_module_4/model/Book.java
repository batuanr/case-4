package com.example.case_module_4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    @ManyToOne
    private Author author;
    @OneToMany(mappedBy = "book")
    private Set<Comment> comments;

    @ManyToOne
    private BookStatus bookStatus;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_category",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categoryList;
    private String description;
    private String image;
}
