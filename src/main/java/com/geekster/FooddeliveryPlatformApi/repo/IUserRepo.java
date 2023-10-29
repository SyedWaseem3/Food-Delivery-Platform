package com.geekster.FooddeliveryPlatformApi.repo;

import com.geekster.FooddeliveryPlatformApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    User findFirstByUserEmail(String email);

}
