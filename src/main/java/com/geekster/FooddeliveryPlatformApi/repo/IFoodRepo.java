package com.geekster.FooddeliveryPlatformApi.repo;

import com.geekster.FooddeliveryPlatformApi.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepo extends JpaRepository<Food, Integer> {
    Food findFirstByFoodName(String foodName);

}
