package com.swetha.transitly.features.user.bussearch;

import com.swetha.transitly.util.ConsoleInput;

import java.util.ArrayList;
import java.util.Scanner;

public class BusSearchView {
    private final BusSearchModel busSearchModel;
    private final Scanner scanner;

    public BusSearchView() {
        busSearchModel = new BusSearchModel(this);
        scanner = ConsoleInput.getScanner();
    }

    public void init() {
        getBusesByStopName();
    }

    public void getBusesByStopName() {
        String stopName;
        while(true) {
            System.out.print("Enter stop name : ");
            stopName = scanner.nextLine().trim();
            if(stopName.isEmpty()) showMessage("Stop name cannot be empty.");
            else break;
        }


        String shift;
        while(true) {
            System.out.print("Enter shift (am / pm) : ");
            shift = scanner.nextLine().trim().toLowerCase();
            if(shift.equals("am") || shift.equals("pm")) break;
            showMessage("Invalid shift. Please enter 'am' or 'pm'.");
        }

        ArrayList<String[]> availableBuses = busSearchModel.getBusesByStopName(stopName, shift);
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

    private void showMessage(String message) {
        System.out.println(message);
    }
}
