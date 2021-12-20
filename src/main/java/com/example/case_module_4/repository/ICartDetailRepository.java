package com.example.case_module_4.repository;

import com.example.case_module_4.model.Cart;
import com.example.case_module_4.model.CartDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail,Long> {
    Iterable<CartDetail> findAllByCart( Cart cart);
}
