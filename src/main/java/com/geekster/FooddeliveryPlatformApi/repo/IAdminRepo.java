package com.geekster.FooddeliveryPlatformApi.repo;

import com.geekster.FooddeliveryPlatformApi.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin, Integer> {

    Admin findFirstByAdminEmail(String email);

}
