package com.darkCoders.TheMarket.repositories;

import com.darkCoders.TheMarket.models.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
