package com.swetha.transitly.features.admin.busmanage;

import com.swetha.transitly.data.dto.Bus;
import com.swetha.transitly.util.ConsoleInput;

import java.util.ArrayList;
import java.util.Scanner;

public class BusManageView {
    private final BusManageModel busManageModel;
    private final Scanner scanner;

    public BusManageView() {
        busManageModel = new BusManageModel(this);
        this.scanner = ConsoleInput.getScanner();
    }

    public void init() {
        showBusMenu();
    }

    public void showBusMenu() {
        while(true) {
            System.out.println("\nBus Management");
            System.out.println("1. Add bus");
            System.out.println("2. View buses");
            System.out.println("3. Back");

            int choice = getChoice("Select an option : ");
            switch(choice) {
                case 1 :
                    addBus();
                    break;

                case 2 :
                    showBusList();
                    break;

                case 3 :
                    return;

                default :
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void addBus() {
        int busId;
        while(true) {
            busId = getChoice("Enter bus id : ");
            if(busId <= 0) showMessage("Bus id must be greater than 0.");
            else if(busManageModel.doesBusIdExist(busId)) showMessage("Bus id already exists.");
            else break;
        }

        String busNumber;
        while(true) {
            System.out.print("Enter bus number : ");
            busNumber = scanner.nextLine().trim();
            if(busNumber.isEmpty()) showMessage("Bus number cannot be empty.");
            else break;
        }

        String busName;
        while(true) {
            System.out.print("Enter bus name : ");
            busName = scanner.nextLine().trim();
            if(busName.isEmpty()) showMessage("Bus name cannot be empty.");
            else break;
        }

        busManageModel.addBus(busId, busNumber, busName);
    }

    public void showBusList() {
        busManageModel.getAllBuses();
    }

    public void onBusAdded(String message) {
        showMessage(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showBusTable(ArrayList<Bus> buses) {
        if(buses == null || buses.isEmpty()) {
            showMessage("No buses available.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s%n", "Bus id", "Bus number", "Bus name");
        System.out.println("--------------------------------------------------------------------------------");

        for(Bus bus : buses) {
            System.out.printf("%-10d %-15s %-15s%n", bus.getBusId(), bus.getBusNumber(), bus.getBusName());
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    private int getChoice(String prompt) {
        System.out.print(prompt);
        while(true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch(NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number : ");
            }
        }
    }
}
