package com.swetha.transitly.features.admin.stoptimingmanage;

import com.swetha.transitly.data.dto.Route;
import com.swetha.transitly.data.dto.StopTiming;
import com.swetha.transitly.util.ConsoleInput;

import java.util.*;

public class StopTimingManageView {
    private StopTimingManageModel stopTimingManageModel;
    private final Scanner scanner;

    public StopTimingManageView() {
        stopTimingManageModel = new StopTimingManageModel(this);
        this.scanner = ConsoleInput.getScanner();
    }

    public void init() {
        showStopTimingMenu();
    }

    public void showStopTimingMenu() {
        while(true) {
            System.out.println("\nStop Timing management");
            System.out.println("1. Add stop timing");
            System.out.println("2. View stop timings");
            System.out.println("3. Back");

            System.out.print("Enter your choice : ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice) {
                case 1:
                    addStopTiming();
                    break;

                case 2:
                    viewStopTimingsByBusRouteId();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void addStopTiming() {
        System.out.print("Enter bus-route id : ");
        int busRouteId = Integer.parseInt(scanner.nextLine());

        Route route = stopTimingManageModel.getRouteByBusRouteId(busRouteId);
        if(route == null) {
            showMessage("Bus-route not found");
            return;
        }

        LinkedHashMap<String, String> stopTimings = new LinkedHashMap<>();

        System.out.println("Enter timings for stops : ");
        for (String stopName : route.getStops()) {
            System.out.print("Enter time for " + stopName + " (HH:MM) : ");
            String arrivalTime = scanner.nextLine();

            stopTimings.put(stopName, arrivalTime);
        }

        stopTimingManageModel.addStopTiming(busRouteId, stopTimings);
        showMessage("All timings have been added successfully.");
    }

    private void viewStopTimingsByBusRouteId() {
        System.out.print("Enter bus-route id : ");
        int busRouteId = Integer.parseInt(scanner.nextLine());

        ArrayList<StopTiming> timings = stopTimingManageModel.getStopTimingsByBusRouteId(busRouteId);

        if(timings.isEmpty()) {
            showMessage("No timings found.");
            return;
        }

        int tripNo = 1;
        for(StopTiming timing : timings) {
            System.out.println("\nTrip " + tripNo++);
            System.out.println("--------------------------------------------------");
            System.out.printf("%-20s %-15s%n", "Stop name", "Arrival time");
            System.out.println("--------------------------------------------------");

            for(Map.Entry<String,String> entry : timing.getStopTimings().entrySet()) {
                System.out.printf("%-20s %-15s%n", entry.getKey(), entry.getValue());
            }

            System.out.println("--------------------------------------------------");
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}


