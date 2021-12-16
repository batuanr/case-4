package com.example.case_module_4.service.cart;

import com.example.case_module_4.model.Cart;
import com.example.case_module_4.model.User;
import com.example.case_module_4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICartService extends IGeneralService<Cart> {
    Page<Cart> findAllByUser(Pageable pageable, User user);
}
