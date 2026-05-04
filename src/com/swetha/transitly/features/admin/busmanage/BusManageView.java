package com.swetha.transitly.features.admin.busmanage;

import com.swetha.transitly.data.dto.Bus;
import com.swetha.transitly.features.admin.routemanage.RouteManageModel;
import com.swetha.transitly.util.ConsoleInput;

import java.util.ArrayList;
import java.util.Scanner;

public class BusManageView {
    private BusManageModel busManageModel;
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
            System.out.println("\nBus management");
            System.out.println("1. Add bus");
            System.out.println("2. View buses");
            System.out.println("3. Back");

            System.out.print("Enter your choice : ");
            int choice = Integer.parseInt(scanner.nextLine());

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
            System.out.print("Enter bus id : ");
            busId = Integer.parseInt(scanner.nextLine());

            if(busManageModel.doesBusIdExist(busId)) showMessage("Bus id already exists.");
            else break;
        }

        System.out.print("Enter bus number : ");
        String busNumber = scanner.nextLine();

        System.out.print("Enter bus name : ");
        String busName = scanner.nextLine();

        busManageModel.addBus(busId, busNumber, busName);
    }

    public void showBusList() {
        busManageModel.getAllBuses();
    }

    public void onBusAdded(String message) {
        showMessage("Bus added successfully.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showBusTable(ArrayList<Bus> buses) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s%n", "Bus id", "Bus number", "Bus name");
        System.out.println("--------------------------------------------------------------------------------");

        for(Bus bus : buses) {
            System.out.printf("%-10d %-15s %-15s%n", bus.getBusId(), bus.getBusNumber(), bus.getBusName());
        }

        System.out.println("--------------------------------------------------------------------------------");
    }
}
