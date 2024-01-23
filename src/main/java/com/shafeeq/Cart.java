package com.shafeeq;

import java.util.ArrayList;
import java.util.List;

import com.shafeeq.model.FoodItem;
import com.shafeeq.model.Restraunt;
import com.shafeeq.model.Pair;

public class Cart {
    public Cart(Restraunt restraunt) {
        this.restraunt = restraunt;
    }

    private Restraunt restraunt;
    private List<Pair> foodItems = new ArrayList<>();
    private int totalAmount = 0;

    public String addFoodItem(Restraunt inpRestraunt, int quantity) {
        FoodItem foodItem = restraunt.getFoodItems().get(0);
        if (restraunt.getRestrauntName().equals(inpRestraunt.getRestrauntName())) {
            Pair foodItemQuant = Pair.builder().foodItem(foodItem).quantity(quantity).build();
        foodItems.add(foodItemQuant);
        totalAmount += (foodItem.getFoodItemPrice() * quantity);
        System.out.println("Total Cart amount: " + totalAmount);
        return "Order Placed Successfully";
        } else {
            return "Cannot place order";
        }
    }
}
