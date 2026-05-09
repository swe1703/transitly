package com.swetha.transitly.features.admin.busroutemanage;

import com.swetha.transitly.data.dto.Bus;
import com.swetha.transitly.data.dto.BusRoute;
import com.swetha.transitly.data.dto.Route;
import com.swetha.transitly.util.InputUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class BusRouteManageView {
    private BusRouteManageModel busRouteManageModel;
    private final Scanner scanner;

    public BusRouteManageView() {
        busRouteManageModel = new BusRouteManageModel(this);
        scanner = InputUtil.getScanner();
    }

    public void init() {
        showBusToRouteMappingMenu();
    }

    void showBusToRouteMappingMenu() {
        while(true) {
            System.out.println("\nBus Route Mapping");
            System.out.println("1. Map bus to route");
            System.out.println("2. View mappings");
            System.out.println("3. Remove mapping (bus-route)");
            System.out.println("4. Back");
            
            int choice = InputUtil.getChoice("Select an option : ");

            switch(choice) {
                case 1 :
                    mapBusToRoute();
                    break;

                case 2 :
                    viewMappings();
                    break;

                case 3 :
                    removeBusRoute();
                    break;

                case 4 :
                    return;

                default :
                    showMessage("Invalid choice.");
            }
        }
    }

    void removeBusRoute() {
        int busRouteId;
        while(true) {
            busRouteId = InputUtil.getChoice("Enter bus-route id to remove (0 to go back) : ");
            if(busRouteId == 0) return;
            if(!busRouteManageModel.doesBusRouteIdExist(busRouteId)) showMessage("Bus-route id does not exist.");
            else break;
        }

        busRouteManageModel.removeBusRoute(busRouteId);
    }

    void mapBusToRoute() {
        int busRouteId;
        while(true) {
            busRouteId = InputUtil.getChoice("Enter bus-route id : ");
            if(busRouteManageModel.doesBusRouteIdExist(busRouteId)) showMessage("Id already exists.");
            else break;
        }

        int busId = InputUtil.getChoice("Enter bus id : ");
        Bus bus = busRouteManageModel.getBusById(busId);
        if(bus == null) {
            showMessage("Bus not found.");
            return;
        }

        int routeId = InputUtil.getChoice("Enter route id : ");
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

    void viewMappings() {
        ArrayList<BusRoute> allMappings = busRouteManageModel.getAllBusRoutes();

        if(allMappings.isEmpty()) {
            showMessage("No mappings found.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-15s %-10s %-12s %-10s%n", "Bus-route id", "Bus id", "Route id", "Shift");
        System.out.println("--------------------------------------------------------------------------------");

        for(BusRoute map : allMappings) {
            System.out.printf("%-15d %-10d %-12d %-10s%n", map.getBusRouteId(), map.getBusId(), map.getRouteId(), map.getShift());
        }

        System.out.println("--------------------------------------------------------------------------------");
    }
}
