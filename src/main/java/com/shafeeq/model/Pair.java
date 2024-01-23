package com.shafeeq.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pair {
    private FoodItem foodItem;
    private int quantity;
}
