package com.shafeeq;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.shafeeq.model.FoodItem;
import com.shafeeq.model.Gender;
import com.shafeeq.model.MainFoodApp;
import com.shafeeq.model.PhoneNumber;
import com.shafeeq.model.Restraunt;
import com.shafeeq.model.SortBy;
import com.shafeeq.model.User;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    private final static MainFoodApp foodApp = new MainFoodApp();
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws LoginException 
     */
    public static void main(String[] args) throws LoginException {
        runTest1();
    }

    private static void runTest1() throws LoginException {
        System.out.println("Starting test: runTest1");
        User user1 = getUser("Pralove", Gender.MALE, "phoneNumber-1", "HSR");
        foodApp.registerUser(user1);

        User user2 = getUser("Nitesh", Gender.MALE, "phoneNumber-2", "BTM");
        foodApp.registerUser(user2);

        User user3 = getUser("Vatsal", Gender.MALE,  "phoneNumber-3", "BTM");
        foodApp.registerUser(user3);

        //======================================================== phoneNumber-1 ========================================================
        foodApp.loginUser("phoneNumber-1");

        Restraunt restraunt1 = getRestraunt("Food Court-1", "BTM/HSR", "NI Thali", 100, 5);
        foodApp.addRestrauntToLoggedInUser(restraunt1);

        Restraunt restraunt2 = getRestraunt("Food Court-2", "BTM", "Burger", 120, 3);
        foodApp.addRestrauntToLoggedInUser(restraunt2);

        //======================================================== phoneNumber-2 ========================================================
        foodApp.loginUser("phoneNumber-2");

        Restraunt restraunt3 = getRestraunt("Food Court-3", "HSR", "SI Thali", 150, 1);
        foodApp.addRestrauntToLoggedInUser(restraunt3);

        //======================================================== phoneNumber-3 ========================================================
        foodApp.loginUser("phoneNumber-3");
        List<Restraunt> restraunts = foodApp.searchRestraunt(SortBy.PRICE);
        printRestraunts(restraunts);

        System.out.println(foodApp.placeOrder("Food Court-1", 2));
        System.out.println(foodApp.placeOrder("Food Court-2", 2));

        foodApp.createReview("Food Court-2", 3, "Good Food");
        foodApp.createReview("Food Court-1", 5, "Nice Food");

        List<Restraunt> ratingRestraunts = foodApp.searchRestraunt(SortBy.RATING);
        printRestraunts(ratingRestraunts);
    }

    private static void printRestraunts(List<Restraunt> restraunts) {
        for (Restraunt restraunt : restraunts) {
            FoodItem foodItem = restraunt.getFoodItems().get(0);
            System.out.println("Restraunt: "
                + restraunt.getRestrauntName()
                + " | FoodItem: " + foodItem.getFoodItemName()
                + " | FoodItem Price: " + foodItem.getFoodItemPrice());
        }
    }

    private static Restraunt getRestraunt(String resName, String servingPinCodeStr, String foodItemName, int foodItemPrice, int foodItemQuantity) {
        List<String> servingPinCodes = List.of(servingPinCodeStr.split("/"));
        FoodItem foodItem = FoodItem
            .builder()
            .foodItemName(foodItemName)
            .foodItemPrice(foodItemPrice)
            .foodItemQuantity(foodItemQuantity)
            .build();
        Restraunt restraunt = Restraunt
            .builder()
            .restrauntName(resName)
            .servingPinCodes(servingPinCodes)
            .foodItems(List.of(foodItem))
            .build();
        return restraunt;
    }

    private static User getUser(String name, Gender gender, String phoneNumberStr, String pinCode) {
        PhoneNumber phoneNumber = PhoneNumber
            .builder()
            .countryCode("+91-")
            .phoneNumber(phoneNumberStr)
            .build();
        return User
            .builder()
            .name(name)
            .gender(gender)
            .phoneNumber(phoneNumber)
            .pincode(pinCode)
            .build();
    }
}
