package com.shafeeq.strategy;

import java.util.Collections;
import java.util.List;

import com.shafeeq.model.Restraunt;

public class RatingSortStrategy implements Strategy{

    @Override
    public List<Restraunt> sortRestraunt(List<Restraunt> restraunts) {
        Collections.sort(restraunts, new RatingComparator());
        System.out.println("Sorting by Rating");
        return restraunts;
    }
    
}
