package com.shafeeq.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.shafeeq.Cart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String name;
    private Gender gender;
    private PhoneNumber phoneNumber;
    private String pincode;
    private String sessionId;
    private String userName;
    private String password;

    @Builder.Default
    private Map<String, Restraunt> ownedRestraunts = new HashMap<>();
    private Cart cart;
    
    public Restraunt getRestrauntByName(String restrauntName) {
        return ownedRestraunts.getOrDefault(restrauntName, null);
    }

    public Restraunt addRestraunt(Restraunt restraunt) {
        ownedRestraunts.put(restraunt.getRestrauntName(), restraunt);
        return restraunt;
    }

    public boolean isLoggedIn() {
        return !sessionId.isEmpty();
    }
    
    public String signIn(String userName, String password, String ipAddress) {
        if (userName == this.userName && password == this.password) {
            this.sessionId = UUID.randomUUID().toString();
            return this.sessionId;
        }
        return null;
    }

    public void logOut() {
        this.sessionId = null;
    }
}
