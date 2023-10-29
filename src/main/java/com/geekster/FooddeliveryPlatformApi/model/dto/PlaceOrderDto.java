package com.geekster.FooddeliveryPlatformApi.model.dto;

import com.geekster.FooddeliveryPlatformApi.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDto {
    AuthenticationInputDto authenticationInputDto;
    Order order;
}
