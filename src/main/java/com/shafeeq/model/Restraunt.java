package com.shafeeq.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Restraunt {
    private String restrauntName;
    private Address address;
    private String pincode;
    private List<String> servingPinCodes;
    private List<FoodItem> foodItems;
    
    @Builder.Default
    private Map<String, UserReview> userReviews = new HashMap<>();
    private double averageRating;

    public double addReview(String userName, UserReview review) {
        int totalReviews = userReviews.size();
        averageRating = (averageRating * totalReviews + review.getRating()) / (totalReviews + 1);
        userReviews.put(userName, review);
        return averageRating;
    }
}
