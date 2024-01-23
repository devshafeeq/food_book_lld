package com.shafeeq.strategy;

import com.shafeeq.model.SortBy;

public class StrategyFactory {
    public Strategy getStrategy(SortBy sortBy) {
        switch (sortBy) {
            case PRICE:
                return new PriceSortStrategy();
            case RATING:
                return new RatingSortStrategy();
            default:
                return null;
        }
    }
}
