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
        System.out.print("Enter stop name : ");
        String stopName = scanner.nextLine();

        System.out.print("Enter shift (am / pm) : ");
        String shift = scanner.nextLine();

        ArrayList<String[]> availableBuses = busSearchModel.getBusesByStopName(stopName, shift);
        if(availableBuses.isEmpty()) {
            showMessage("No buses available for your stop.");
        }

        System.out.println("\n---------------------------------------------------------");
        System.out.printf(" %-4s %-16s %-14s %s%n", "S.no", "Bus no", "Bus name", "Arrival time");
        System.out.println("---------------------------------------------------------");

        int serialNo = 1;
        for(String[] busDetails : availableBuses) {
            System.out.printf(" %-4d %-16s %-14s %s%n", serialNo++, busDetails[0], busDetails[1], busDetails[2]);
        }

        System.out.println("---------------------------------------------------------");
    }

    private void showMessage(String message) {
        System.out.println(message);
    }
}
