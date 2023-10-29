package com.geekster.FooddeliveryPlatformApi.service;

import com.geekster.FooddeliveryPlatformApi.model.Food;
import com.geekster.FooddeliveryPlatformApi.model.Order;
import com.geekster.FooddeliveryPlatformApi.model.User;
import com.geekster.FooddeliveryPlatformApi.model.customExceptions.UnauthorizedException;
import com.geekster.FooddeliveryPlatformApi.model.dto.AuthenticationInputDto;
import com.geekster.FooddeliveryPlatformApi.model.enums.Status;
import com.geekster.FooddeliveryPlatformApi.repo.IOrderRepo;
import com.geekster.FooddeliveryPlatformApi.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    IOrderRepo orderRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    IUserRepo userRepo;


    public String placeOrder(AuthenticationInputDto authenticationInputDto, Order order) {
        String email = authenticationInputDto.getEmail();
        String tokenValue = authenticationInputDto.getTokenValue();
        if(authenticationService.authenticate(email, tokenValue)){
            User existingUser = userRepo.findFirstByUserEmail(email);
            Food food = order.getFood();
            order.setUser(existingUser);
            order.setFood(food);
            order.setOrderCreationTimeStamp(LocalDateTime.now());
            orderRepo.save(order);
            return "Order has been placed!";

        }else{
            return "Un authenticated access!!";
        }
    }

    public String cancelOrder(AuthenticationInputDto authenticationInputDto, Integer orderId) {
        String email = authenticationInputDto.getEmail();
        String tokenValue = authenticationInputDto.getTokenValue();

        if(authenticationService.authenticate(email, tokenValue)){
            Order existingOrder = orderRepo.findById(orderId).orElseThrow();
            User existingUser = userRepo.findFirstByUserEmail(email);
            if(existingOrder.getUser().equals(existingUser)) {
                orderRepo.delete(existingOrder);
            }else{
                return "Un authorized cancel order!!";
            }
            return "Order with ID " +existingOrder.getOrderId() + " was canceled!";
        }else{
            return "Un Authenticated Access!";
        }
    }

    public String updateOrderStatus(Integer orderId, Status newStatus, String adminEmail, String tokenValue) {
        if(authenticationService.authenticateAdmin(adminEmail, tokenValue)) {
            if (adminEmail.endsWith("@admin.com")) {
                Order existingOrder = orderRepo.findFirstByOrderId(orderId);
                if(existingOrder != null){
                    existingOrder.setOrderStatusFood(newStatus);
                    orderRepo.save(existingOrder);
                    return "Order status has been updated";
                }else{
                    return "Cannot find the order!!";
                }
            } else {
                return "Only admins can update order status";
            }
        }else{
            return "Un authenticated access!";
        }
    }

    public List<Order> getAllOrders(String adminEmail, String tokenValue) {
        if(authenticationService.authenticateAdmin(adminEmail, tokenValue)){
            if (adminEmail.endsWith("@admin.com")) {
                List<Order> allOrders = orderRepo.findAll();
                return allOrders;
            }else{
                throw new UnauthorizedException("Only admins can view all the orders!!");
            }
        }else{
            throw new UnauthorizedException("Un authenticated access!!");
        }
    }
}
