package com.example.case_module_4.service.cartDetail;

import com.example.case_module_4.model.Cart;
import com.example.case_module_4.model.CartDetail;
import com.example.case_module_4.model.User;
import com.example.case_module_4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICartDetailService extends IGeneralService<CartDetail> {
    Iterable<CartDetail> findAllByCart( Cart cart);

}
