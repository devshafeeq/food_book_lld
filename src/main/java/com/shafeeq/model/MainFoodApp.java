package com.shafeeq.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import com.shafeeq.Cart;
import com.shafeeq.strategy.StrategyFactory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainFoodApp {
    private Map<String, User> phoneNumberUserMap = new HashMap<>();
    private User loggedInUser;
    private Map<String, List<Restraunt>> pinCodeToRestrMap = new HashMap<>();
    private Map<String, Restraunt> resNameToResMap = new HashMap<>();
    private StrategyFactory sortinStrategyFactory = new StrategyFactory();
    

    public User getUserByPhoneNumber(String phoneNumber) {
        return phoneNumberUserMap.getOrDefault(phoneNumber, null);
    }

    public User registerUser(User user) {
        phoneNumberUserMap.put(user.getPhoneNumber().getPhoneNumber(), user);
        System.out.println("Registered user: " + user.getName());
        return user;
    }

    public User loginUser(String phoneNumber) {
        User user = getUserByPhoneNumber(phoneNumber);
        if (null != user) {
            loggedInUser = user;
        }
        System.out.println("Logged In user: " + user.getName());
        return user;
    }

    public Restraunt addRestrauntToLoggedInUser(Restraunt restraunt) throws LoginException {
        User loggedInUser = this.loggedInUser;
        if(null == loggedInUser) {
            throw new LoginException("No user logged in");
        }
        List<String> servingPincodes = restraunt.getServingPinCodes();
        for (String pinCode : servingPincodes) {
            List<Restraunt> pinCodeRestraunts = pinCodeToRestrMap.get(pinCode);
            if (null == pinCodeRestraunts) {
                pinCodeRestraunts = new ArrayList<>();
                pinCodeToRestrMap.put(pinCode, pinCodeRestraunts);
            }
            pinCodeRestraunts.add(restraunt);
        }
        resNameToResMap.put(restraunt.getRestrauntName(), restraunt);
        System.out.println("Added Restraunt: " + restraunt.getRestrauntName() + " to logged in user: " + loggedInUser.getName());
        return loggedInUser.addRestraunt(restraunt);
    }

    public List<Restraunt> searchRestraunt(SortBy sortBy) throws LoginException {
        if (null == loggedInUser) {
            throw new LoginException("No user logged in");
        }
        for (Map.Entry<String, List<Restraunt>> entry : pinCodeToRestrMap.entrySet()) {
            System.out.println("    ==> PinCode: " + entry.getKey());
            for (Restraunt restraunt : entry.getValue()) {
                System.out.println("    ==> Resturant: " + restraunt.getRestrauntName() + " | Rating: " + restraunt.getAverageRating() + " | ServiceAre: " + restraunt.getServingPinCodes());
            }
        }
        List<Restraunt> restraunts = pinCodeToRestrMap.getOrDefault(loggedInUser.getPincode(), new ArrayList<>());
        sortinStrategyFactory.getStrategy(sortBy).sortRestraunt(restraunts);
        return restraunts;
    }

    public String placeOrder(String restrauntName, int quantity) throws LoginException {
        if (null == loggedInUser) {
            throw new LoginException("No user logged in");
        }
        Cart cart = loggedInUser.getCart();
        Restraunt restraunt = resNameToResMap.getOrDefault(restrauntName, null);
        if (null == restraunt) {
            throw new LoginException("Invalid Resturant Name");
        }
        if (null == cart) {
            cart = new Cart(restraunt);
            loggedInUser.setCart(cart);
        }
        System.out.println("Placed order in Restraunt: " + restraunt.getRestrauntName() + " to cart of logged in user: " + loggedInUser.getName());
        return cart.addFoodItem(restraunt, quantity);
    }

    public void createReview(String resName, int rating, String reviewComment) {
        Restraunt restraunt = resNameToResMap.get(resName);
        double avgRating = restraunt.addReview(loggedInUser.getName(), UserReview.builder().rating(rating).comment(reviewComment).build());
        System.out.println("Average Rating: " + avgRating);
    }
}
