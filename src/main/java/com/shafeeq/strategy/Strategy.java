package com.shafeeq.strategy;

import java.util.List;

import com.shafeeq.model.Restraunt;

public interface Strategy {
    public List<Restraunt> sortRestraunt(List<Restraunt> restraunts);
}
