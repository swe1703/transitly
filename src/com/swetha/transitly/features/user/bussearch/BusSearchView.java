package com.swetha.transitly.features.user.bussearch;

import com.swetha.transitly.util.InputUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class BusSearchView {
    private final BusSearchModel busSearchModel;
    private final Scanner scanner;

    public BusSearchView() {
        busSearchModel = new BusSearchModel(this);
        scanner = InputUtil.getScanner();
    }

    public void init() {
        getBusesBySourceAndDestination();
    }

    void getBusesBySourceAndDestination() {
        String source;
        while(true) {
            System.out.print("Starting from : ");
            source = scanner.nextLine().trim();
            if(source.isEmpty()) showMessage("Source  cannot be empty.");
            else break;
        }

        String destination;
        while(true) {
            System.out.print("Heading to : ");
            destination = scanner.nextLine().trim();
            if(destination.isEmpty()) showMessage("Destination cannot be empty.");
            else break;
        }


        String shift;
        while(true) {
            System.out.print("Enter shift (am / pm) : ");
            shift = scanner.nextLine().trim().toLowerCase();
            if(shift.equals("am") || shift.equals("pm")) break;
            showMessage("Invalid shift. Please enter 'am' or 'pm'.");
        }

        ArrayList<String[]> availableBuses = busSearchModel.getBusesBySourceAndDestination(source, destination, shift);
        if(availableBuses == null || availableBuses.isEmpty()) {
            showMessage("No buses available for your stop.");
        }

        else {
            System.out.println("\n---------------------------------------------------------");
            System.out.printf(" %-4s %-16s %-14s %s%n", "S.no", "Bus no", "Bus name", "Arrival time");
            System.out.println("---------------------------------------------------------");

            int serialNo = 1;
            for(String[] busDetails : availableBuses) {
                System.out.printf(" %-4d %-16s %-14s %s%n", serialNo++, busDetails[0], busDetails[1], busDetails[2]);
            }

            System.out.println("---------------------------------------------------------");
        }

        System.out.print("\nPress 'Enter' to continue.");
        scanner.nextLine();
    }

    void showMessage(String message) {
        System.out.println(message);
    }
}
