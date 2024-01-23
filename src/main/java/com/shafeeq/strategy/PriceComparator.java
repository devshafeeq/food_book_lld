package com.shafeeq.strategy;

import java.util.Comparator;

import com.shafeeq.model.FoodItem;
import com.shafeeq.model.Restraunt;

public class PriceComparator implements Comparator<Restraunt>{

    @Override
    public int compare(Restraunt o1, Restraunt o2) {
        FoodItem foodItem1 = o1.getFoodItems().get(0);
        FoodItem foodItem2 = o2.getFoodItems().get(0);
        return foodItem1.getFoodItemPrice() - foodItem2.getFoodItemPrice();
    }
    
}
