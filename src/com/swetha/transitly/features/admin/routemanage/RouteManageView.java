package com.swetha.transitly.features.admin.routemanage;

import com.swetha.transitly.data.dto.Route;
import com.swetha.transitly.util.ConsoleInput;

import java.util.ArrayList;
import java.util.Scanner;

public class RouteManageView {
    private RouteManageModel routeManageModel;
    private final Scanner scanner;

    public RouteManageView() {
        routeManageModel = new RouteManageModel(this);
        scanner = ConsoleInput.getScanner();
    }

    public void init() {
        showRouteMenu();
    }

    public void showRouteMenu() {
        while(true) {
            System.out.println("\nRoute management");
            System.out.println("1. Add route");
            System.out.println("2. View routes");
            System.out.println("3. Back");

            System.out.print("Enter your choice : ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice) {
                case 1:
                    addRoute();
                    break;

                case 2:
                    showRouteList();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void showRouteList() {
        routeManageModel.getAllRoutes();
    }

    public void addRoute() {
        int routeId;
        while(true) {
            System.out.print("Enter route id : ");
            routeId = Integer.parseInt(scanner.nextLine());

            if(routeManageModel.doesRouteExist(routeId)) showMessage("Route id already exists.");
            else break;
        }

        System.out.print("Enter number of stops : ");
        int stopsCount = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> stops = new ArrayList<>();

        for(int i = 1; i <= stopsCount; i++) {
            System.out.print("Enter Stop " + i + " : ");
            stops.add(scanner.nextLine());
        }

        String source = stops.get(0);
        String destination = stops.get(stopsCount-1);

        routeManageModel.addRoute(routeId, source, destination, stops);
    }

    public void onRouteAdded(String message) {
        showMessage("Route added successfully.");
    }

    public void showRouteTable(ArrayList<Route> routes) {
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-40s%n", "Route id", "Source", "Destination", "Stops");
        System.out.println("-------------------------------------------------------");

        for(Route route : routes) {
            System.out.printf("%-10d %-15s %-15s %-40s%n", route.getRouteId(), route.getSource(), route.getDestination(), String.join(" -> ", route.getStops()));
        }

        System.out.println("-------------------------------------------------------");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
