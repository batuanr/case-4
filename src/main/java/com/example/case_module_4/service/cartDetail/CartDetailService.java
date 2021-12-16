package com.example.case_module_4.service.cartDetail;

import com.example.case_module_4.model.CartDetail;
import com.example.case_module_4.repository.ICartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartDetailService implements ICartDetailService{
    @Autowired
    private ICartDetailRepository cartDetailRepository;

    @Override
    public Iterable<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public void save(CartDetail cartDetail) {
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public void remove(Long id) {
        cartDetailRepository.deleteById(id);
    }
}
