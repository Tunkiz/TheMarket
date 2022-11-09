package com.darkCoders.TheMarket.repositories;

import com.darkCoders.TheMarket.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
