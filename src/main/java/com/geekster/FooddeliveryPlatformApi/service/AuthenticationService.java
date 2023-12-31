package com.geekster.FooddeliveryPlatformApi.service;

import com.geekster.FooddeliveryPlatformApi.model.AuthenticationToken;
import com.geekster.FooddeliveryPlatformApi.repo.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authenticationRepo;

    public void create(AuthenticationToken token) {
        authenticationRepo.save(token);
    }

    public boolean authenticate(String email, String tokenValue) {
        AuthenticationToken token = authenticationRepo.findFirstByTokenValue(tokenValue);
        if(token != null){
            return token.getUser().getUserEmail().equals(email);
        }else{
            return false;
        }
    }

    public boolean authenticateAdmin(String email, String tokenValue) {
        AuthenticationToken token = authenticationRepo.findFirstByTokenValue(tokenValue);
        if(token != null){
            return token.getAdmin().getAdminEmail().equals(email);
        }else{
            return false;
        }
    }

    public void deleteToken(String tokenValue) {
        AuthenticationToken token = authenticationRepo.findFirstByTokenValue(tokenValue);
        authenticationRepo.delete(token);
    }
}
