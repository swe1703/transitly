package com.swetha.transitly.features.admin.busmanage;

import com.swetha.transitly.data.dto.Bus;
import com.swetha.transitly.util.InputUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class BusManageView {
    private final BusManageModel busManageModel;
    private final Scanner scanner;

    public BusManageView() {
        busManageModel = new BusManageModel(this);
        this.scanner = InputUtil.getScanner();
    }

    public void init() {
        showBusMenu();
    }

    void showBusMenu() {
        while(true) {
            System.out.println("\nBus Management");
            System.out.println("1. Add bus");
            System.out.println("2. View buses");
            System.out.println("3. Remove bus");
            System.out.println("4. Back");

            int choice = InputUtil.getChoice("Select an option : ");
            switch(choice) {
                case 1 :
                    addBus();
                    break;

                case 2 :
                    showBusList();
                    break;

                case 3 :
                    removeBus();
                    break;

                case 4 :
                    return;

                default :
                    System.out.println("Invalid choice.");
            }
        }
    }

    void removeBus() {
        int busId;
        while(true) {
            busId = InputUtil.getChoice("Enter bus id to remove (0 to go back) : ");

            if(busId == 0) return;
            if(busId < 0) showMessage("Bus id must be greater than 0.");
            else if(!busManageModel.doesBusIdExist(busId)) showMessage("Bus id does not exist.");
            else break;
        }

        busManageModel.removeBus(busId);
    }

    void addBus() {
        int busId;
        while(true) {
            busId = InputUtil.getChoice("Enter bus id : ");
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

    void showBusList() {
        busManageModel.getAllBuses();
    }

    void onBusAdded(String message) {
        showMessage(message);
    }

    void showMessage(String message) {
        System.out.println(message);
    }

    void showBusTable(ArrayList<Bus> buses) {
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
}
