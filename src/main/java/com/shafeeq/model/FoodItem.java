package com.shafeeq.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodItem {
    private String foodItemName;
    private int foodItemPrice;
    private int foodItemQuantity;

    public FoodItem updateFoodItem(FoodItem foodItem) {
        throw new UnsupportedOperationException();
        // return null;
    }
}
