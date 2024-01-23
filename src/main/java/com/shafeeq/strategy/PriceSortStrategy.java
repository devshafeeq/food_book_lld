package com.shafeeq.strategy;

import java.util.Collections;
import java.util.List;

import com.shafeeq.model.Restraunt;

public class PriceSortStrategy implements Strategy{

    @Override
    public List<Restraunt> sortRestraunt(List<Restraunt> restraunts) {
        Collections.sort(restraunts, new PriceComparator());
        System.out.println("Sorting by Price");
        return restraunts;
    }
    
}
