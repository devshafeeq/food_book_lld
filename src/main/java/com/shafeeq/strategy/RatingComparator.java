package com.shafeeq.strategy;

import java.util.Comparator;

import com.shafeeq.model.Restraunt;

public class RatingComparator implements Comparator<Restraunt>{

    @Override
    public int compare(Restraunt o1, Restraunt o2) {
        return (int)(o1.getAverageRating() - o2.getAverageRating());
    }
    
}
