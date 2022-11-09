package com.darkCoders.TheMarket.repositories;

import com.darkCoders.TheMarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products WHERE name = ?1",nativeQuery = true)
    Product findByName(String name);
}
