package com.example.case_module_4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    @ManyToMany(mappedBy = "categoryList")
    private Set<Book> books;

    public Category() {
    }

    public Category(String category, Set<Book> books) {
        this.category = category;
        this.books = books;
    }

    public Category(Long id, String category, Set<Book> books) {
        this.id = id;
        this.category = category;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", books=" + books +
                '}';
    }
}
