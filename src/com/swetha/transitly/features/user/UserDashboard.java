package com.swetha.transitly.features.user;

import com.swetha.transitly.features.user.bussearch.BusSearchView;
import com.swetha.transitly.util.ConsoleInput;

import java.util.Scanner;

public class UserDashboard {
    private final Scanner scanner = ConsoleInput.getScanner();

    public void showUserDashboard() {
        while(true) {
            System.out.println("\nUser Dashboard");
            System.out.println("1. Search available buses by stop name");
            System.out.println("2. Logout");

            System.out.print("Select an option : ");
            int choice;

            while(true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                }

                catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number : ");
                }
            }

            switch(choice) {
                case 1 :
                    new BusSearchView().init();
                    break;

                case 2 :
                    System.out.println("Logged out successfully.");
                    return;

                default :
                    System.out.println("Invalid choice.");
            }
        }
    }
}
