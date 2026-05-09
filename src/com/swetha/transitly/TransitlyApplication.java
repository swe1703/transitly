package com.swetha.transitly;

import com.swetha.transitly.features.authentication.adminsignin.AdminSignInView;
import com.swetha.transitly.features.authentication.signin.SignInView;
import com.swetha.transitly.features.authentication.signup.SignUpView;
import com.swetha.transitly.util.InputUtil;

import java.util.Scanner;

public class TransitlyApplication {
    private static final Scanner scanner = InputUtil.getScanner();

    public static void main(String[] args) {
        while(true) {

            System.out.println("\nTRANSITLY");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");

            int choice = InputUtil.getChoice("Select an option : ");
            switch(choice) {
                case 1 :
                    new AdminSignInView().init();
                    break;

                case 2 :
                    boolean isUserMenuRunning = true;
                    while(isUserMenuRunning) {
                        System.out.println("\nUser options : ");
                        System.out.println("1. Sign Up");
                        System.out.println("2. Sign In");
                        System.out.println("3. Back");

                        int userChoice = InputUtil.getChoice("Enter your choice : ");
                        switch(userChoice) {
                            case 1 :
                                new SignUpView().init();
                                break;

                            case 2 :
                                new SignInView().init();
                                break;

                            case 3 :
                                isUserMenuRunning = false;
                                break;

                            default :
                                System.out.println("Invalid choice.");
                        }
                    }
                    break;

                case 3 :
                    System.out.println("Thank you!");
                    return;

                default :
                    System.out.println("Invalid choice.");
            }
        }
    }
}