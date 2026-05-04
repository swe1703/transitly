package com.swetha.transitly;

import com.swetha.transitly.features.authentication.adminsignin.AdminSignInView;
import com.swetha.transitly.features.authentication.signin.SignInView;
import com.swetha.transitly.features.authentication.signup.SignUpView;

import java.util.Scanner;

public class TransitlyApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("\nTRANSITLY");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");

            System.out.print("Select an option : ");
            int choice = scanner.nextInt();

            switch(choice) {
                case 1 :
                    new AdminSignInView().init();
                    break;

                case 2 :
                    while(true) {
                        System.out.println("\nUser options : ");
                        System.out.println("1. Sign Up");
                        System.out.println("2. Sign In");

                        System.out.print("Enter your choice : ");
                        int userChoice = scanner.nextInt();

                        if(userChoice == 1) {
                            boolean success = new SignUpView().init();
                            if(success) new SignInView().init();
                        }

                        else if(userChoice == 2) {
                            boolean success = new SignInView().init();
                            if(success) break;
                        }

                        else System.out.println("Invalid choice.");
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