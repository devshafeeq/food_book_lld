package com.shafeeq.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserReview {
    private int rating;
    private String comment;
}
