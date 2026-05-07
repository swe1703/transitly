package com.swetha.transitly.features.admin.stoptimingmanage;

import com.swetha.transitly.data.dto.Route;
import com.swetha.transitly.data.dto.StopTiming;
import com.swetha.transitly.util.ConsoleInput;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class StopTimingManageView {
    private final StopTimingManageModel stopTimingManageModel;
    private final Scanner scanner;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public StopTimingManageView() {
        stopTimingManageModel = new StopTimingManageModel(this);
        this.scanner = ConsoleInput.getScanner();
    }

    public void init() {
        showStopTimingMenu();
    }

    public void showStopTimingMenu() {
        while(true) {
            System.out.println("\nStop Timing Management");
            System.out.println("1. Add stop timing");
            System.out.println("2. View stop timings");
            System.out.println("3. Back");

            int choice = getChoice("Select an option : ");
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
        int busRouteId;
        while(true) {
            busRouteId = getChoice("Enter bus-route id : ");
            if(busRouteId <= 0) showMessage("Bus-route id must be greater than 0.");
            else break;
        }

        Route route = stopTimingManageModel.getRouteByBusRouteId(busRouteId);
        if(route == null) {
            showMessage("Bus-route not found.");
            return;
        }

        LinkedHashMap<String, String> stopTimings = new LinkedHashMap<>();

        System.out.println("Enter timings for stops : ");
        for(String stopName : route.getStops()) {
            while(true) {
                System.out.print("Enter time for " + stopName + " (HH:MM) : ");
                String arrivalTime = scanner.nextLine().trim();

                if(arrivalTime.isEmpty()) {
                    showMessage("Arrival time cannot be empty.");
                    continue;
                }

                try {
                    LocalTime.parse(arrivalTime, TIME_FORMATTER);
                    stopTimings.put(stopName, arrivalTime);
                    break;
                } catch(DateTimeParseException e) {
                    showMessage("Invalid time format. Please use HH:MM.");
                }
            }
        }

        stopTimingManageModel.addStopTiming(busRouteId, stopTimings);
        showMessage("All timings have been added successfully.");
    }

    private void viewStopTimingsByBusRouteId() {
        int busRouteId = getChoice("Enter bus-route id : ");
        ArrayList<StopTiming> timings = stopTimingManageModel.getStopTimingsByBusRouteId(busRouteId);
        if(timings == null || timings.isEmpty()) {
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


