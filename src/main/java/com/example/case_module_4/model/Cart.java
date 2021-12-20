package com.example.case_module_4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Date repayDate;
    private Boolean status;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "cart")
    @JsonIgnore
    private Set<CartDetail> cartDetails;
}
