package com.darkCoders.TheMarket.services;

import com.darkCoders.TheMarket.models.Cart;
import com.darkCoders.TheMarket.models.exceptions.CartNotFoundException;
import com.darkCoders.TheMarket.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServices {
    private final CartRepository cartRepository;
    @Autowired
    public CartServices(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public Cart addCart(Cart newCart){
        return cartRepository.save(newCart);
    }
    public Cart getCart(long id){
        return cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
    }
}
