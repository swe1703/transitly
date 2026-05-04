package com.swetha.transitly.features.admin.busroutemanage;

import com.swetha.transitly.data.dto.Bus;
import com.swetha.transitly.data.dto.BusRoute;
import com.swetha.transitly.data.dto.Route;
import com.swetha.transitly.util.ConsoleInput;

import java.util.ArrayList;
import java.util.Scanner;

public class BusRouteManageView {
    private BusRouteManageModel busRouteManageModel;
    private final Scanner scanner;

    public BusRouteManageView() {
        busRouteManageModel = new BusRouteManageModel(this);
        scanner = ConsoleInput.getScanner();
    }

    public void init() {
        showBusToRouteMappingMenu();
    }

    private void showBusToRouteMappingMenu() {
        while(true) {
            System.out.println("\nBus Route Mapping");
            System.out.println("1. Map bus to route");
            System.out.println("2. View mappings");
            System.out.println("3. Back");

            System.out.print("Enter your choice : ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice) {
                case 1 :
                    mapBusToRoute();
                    break;

                case 2 :
                    viewMappings();
                    break;

                case 3 :
                    return;

                default :
                    showMessage("Invalid choice.");
            }
        }
    }

    private void mapBusToRoute() {
        int busRouteId;

        while(true) {
            System.out.print("Enter bus-route id : ");
            busRouteId = Integer.parseInt(scanner.nextLine());

            if(busRouteManageModel.doesBusRouteIdExist(busRouteId)) showMessage("Id already exists.");
            else break;
        }

        System.out.print("Enter bus id : ");
        int busId = Integer.parseInt(scanner.nextLine());

        Bus bus = busRouteManageModel.getBusById(busId);
        if(bus == null) {
            showMessage("Bus not found.");
            return;
        }

        System.out.print("Enter route id : ");
        int routeId = Integer.parseInt(scanner.nextLine());

        Route route = busRouteManageModel.getRouteById(routeId);
        if(route == null) {
            showMessage("Route not found.");
            return;
        }

        String shift;
        while(true) {
            System.out.print("Enter shift (am / pm) : ");
            shift = scanner.nextLine().trim().toLowerCase();

            if(shift.equals("am") || shift.equals("pm")) break;
            showMessage("Invalid shift. Enter only am or pm.");
        }

        busRouteManageModel.addBusRoute(busRouteId, busId, routeId, shift);
    }

    void showMessage(String message) {
        System.out.println(message);
    }

    private void viewMappings() {
        ArrayList<BusRoute> allMappings = busRouteManageModel.getAllBusRoutes();

        if(allMappings.isEmpty()) {
            showMessage("No mappings found.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-12s %-10s %-10s %-10s%n", "Bus-route id", "Bus id", "Route id", "Shift");
        System.out.println("--------------------------------------------------------------------------------");

        for(BusRoute map : allMappings) {
            System.out.printf("%-12d %-10d %-10d %-10s%n", map.getBusRouteId(), map.getBusId(), map.getRouteId(), map.getShift());
        }

        System.out.println("--------------------------------------------------------------------------------");
    }
}
