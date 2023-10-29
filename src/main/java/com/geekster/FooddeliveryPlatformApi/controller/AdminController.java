package com.geekster.FooddeliveryPlatformApi.controller;

import com.geekster.FooddeliveryPlatformApi.model.Admin;
import com.geekster.FooddeliveryPlatformApi.model.Food;
import com.geekster.FooddeliveryPlatformApi.model.Order;
import com.geekster.FooddeliveryPlatformApi.model.User;
import com.geekster.FooddeliveryPlatformApi.model.enums.Status;
import com.geekster.FooddeliveryPlatformApi.service.AdminService;
import com.geekster.FooddeliveryPlatformApi.service.FoodService;
import com.geekster.FooddeliveryPlatformApi.service.OrderService;
import com.geekster.FooddeliveryPlatformApi.service.UserService;
import com.geekster.FooddeliveryPlatformApi.service.priceUpdater.FoodPriceUpdater;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    FoodService foodService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;


    //Admin sign up
    @PostMapping("admin/signUp")
    public String adminSignUp(@RequestBody @Valid Admin newAdmin){
        return adminService.adminSignUp(newAdmin);
    }

    //Admin sign in
    @PostMapping("admin/signIn/email/{email}/password/{password}")
    public String adminSignIn(@PathVariable String email, @PathVariable String password){
        return adminService.adminSignIn(email, password);
    }

    //Admin sign out
    @DeleteMapping("admin/signOut/email/{email}/tokenValue/{tokenValue}")
    public String adminSignOut(@PathVariable String email, @PathVariable String tokenValue){
        return adminService.adminSignOut(email, tokenValue);
    }

    //Add new food
    @PostMapping("addFood/adminEmail/{adminEmail}/tokenValue/{tokenValue}")
    public String addFood(@RequestBody Food newFood, @PathVariable String adminEmail, @PathVariable String tokenValue){
        return foodService.addFood(newFood, adminEmail, tokenValue);
    }

    //Get all foods
    @GetMapping("foods/adminEmail/{adminEmail}/tokenValue/{tokenValue}")
    public List<Food> getAllFoods(@PathVariable String adminEmail, @PathVariable String tokenValue){
        return foodService.getAllFoods(adminEmail, tokenValue);
    }


    //Updating the price of a particular food item
    @PutMapping("updateFood/adminEmail/{adminEmail}/tokenValue/{tokenValue}")
    public String updateFoodByName(@RequestBody FoodPriceUpdater foodPriceUpdater, @PathVariable String adminEmail, @PathVariable String tokenValue){
        return foodService.updateFoodByName(foodPriceUpdater.getName(), foodPriceUpdater.getPrice(), adminEmail, tokenValue);
    }

    //Update food status
    @PutMapping("updateOrderStatus/orderId/{orderId}/adminEmail/{adminEmail}/tokenValue/{tokenValue}")
    public String updateOrderStatus(@PathVariable Integer orderId, @RequestParam Status newStatus, @PathVariable String adminEmail, @PathVariable String tokenValue){
        return orderService.updateOrderStatus(orderId, newStatus, adminEmail, tokenValue);
    }

    //Delete the food by id
    @DeleteMapping("deleteFood/foodId/{foodId}/adminEmail/{adminEmail}/tokenValue/{tokenValue}")
    public String deleteFoodById(@PathVariable Integer foodId,@PathVariable String adminEmail, @PathVariable String tokenValue){
        return foodService.deleteFoodById(foodId, adminEmail, tokenValue);
    }

    //Get all users
    @GetMapping("users/adminEmail/{adminEmail}/tokenValue/{tokenValue}")
    public List<User> getAllUsers(@PathVariable String adminEmail, @PathVariable String tokenValue){
        return userService.getAllUsers(adminEmail, tokenValue);
    }

    //Get user by id
    @GetMapping("user/{userId}/adminEmail/{adminEmail}/tokenValue/{tokenValue}")
    public User getUserById(@PathVariable Integer userId,@PathVariable String adminEmail, @PathVariable String tokenValue){
        return userService.getUserById(userId, adminEmail, tokenValue);
    }


    //Get all orders
    @GetMapping("orders/adminEmail/{adminEmail}/tokenValue/{tokenValue}")
    public List<Order> getAllOrders(@PathVariable String adminEmail, String tokenValue){
        return orderService.getAllOrders(adminEmail, tokenValue);
    }
}
