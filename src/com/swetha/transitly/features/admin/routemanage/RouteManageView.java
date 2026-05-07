package com.swetha.transitly.features.admin.routemanage;

import com.swetha.transitly.data.dto.Route;
import com.swetha.transitly.util.ConsoleInput;

import java.util.ArrayList;
import java.util.Scanner;

public class RouteManageView {
    private final RouteManageModel routeManageModel;
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
            System.out.println("3. Remove route");
            System.out.println("4. Back");

            int choice = getChoice("Select an option : ");
            switch(choice) {
                case 1 :
                    addRoute();
                    break;

                case 2 :
                    showRouteList();
                    break;

                case 3 :
                    removeRoute();
                    break;

                case 4 :
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void removeRoute() {
        int routeId;

        while(true) {
            routeId = getChoice("Enter route id to remove (0 to go back) : ");

            if(routeId == 0) return;
            if(routeId < 0) showMessage("Route id must be greater than 0.");
            else if(!routeManageModel.doesRouteExist(routeId)) showMessage("Route id does not exist.");
            else break;
        }

        routeManageModel.removeRoute(routeId);
    }

    public void showRouteList() {
        routeManageModel.getAllRoutes();
    }

    public void addRoute() {
        int routeId;
        while(true) {
            routeId = getChoice("Enter route id : ");
            if(routeId <= 0) showMessage("Route id must be greater than 0.");
            else if(routeManageModel.doesRouteExist(routeId)) showMessage("Route id already exists.");
            else break;
        }

        int stopsCount;
        while(true) {
            stopsCount = getChoice("Enter number of stops : ");
            if(stopsCount <= 0) showMessage("Stops count must be greater than 0.");
            else break;
        }

        ArrayList<String> stops = new ArrayList<>();
        for(int i = 1; i <= stopsCount; i++) {
            while(true) {
                System.out.print("Enter Stop " + i + " : ");
                String stopName = scanner.nextLine().trim();

                if(stopName.isEmpty()) showMessage("Stop name cannot be empty.");
                else {
                    stops.add(stopName);
                    break;
                }
            }
        }

        String source = stops.get(0);
        String destination = stops.get(stops.size()-1);

        routeManageModel.addRoute(routeId, source, destination, stops);
    }

    public void onRouteAdded(String message) {
        showMessage(message);
    }

    public void showRouteTable(ArrayList<Route> routes) {
        if(routes == null || routes.isEmpty()) {
            showMessage("No routes available.");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-50s%n", "Route id", "Source", "Destination", "Stops");
        System.out.println("----------------------------------------------------------------------------------------------------");

        for(Route route : routes) {
            String stops = String.join(" -> ", route.getStops());
            System.out.printf("%-10d %-15s %-15s %-50s%n", route.getRouteId(), route.getSource(), route.getDestination(), stops);
        }

        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    public void showMessage(String message) {
        System.out.println(message);
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
