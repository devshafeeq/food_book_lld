package com.shafeeq.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneNumber {
    private String phoneNumber;
    private String countryCode;

    public boolean isValidPhone(String phnNumber) {
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phnNumber);
        return matcher.matches();
    }
}
