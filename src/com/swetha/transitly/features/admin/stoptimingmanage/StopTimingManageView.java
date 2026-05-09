package com.swetha.transitly.features.admin.stoptimingmanage;

import com.swetha.transitly.data.dto.Route;
import com.swetha.transitly.data.dto.StopTiming;
import com.swetha.transitly.util.InputUtil;

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
        this.scanner = InputUtil.getScanner();
    }

    public void init() {
        showStopTimingMenu();
    }

     void showStopTimingMenu() {
        while(true) {
            System.out.println("\nStop Timing Management");
            System.out.println("1. Add stop timing");
            System.out.println("2. View stop timings");
            System.out.println("3. Update stop timing");
            System.out.println("4. Back");

            int choice = InputUtil.getChoice("Select an option : ");
            switch(choice) {
                case 1:
                    addStopTiming();
                    break;

                case 2:
                    viewStopTimingsByBusRouteId();
                    break;

                case 3:
                    updateStopTiming();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    void updateStopTiming() {
        int busRouteId = InputUtil.getChoice("Enter bus-route id : ");

        ArrayList<StopTiming> timings = stopTimingManageModel.getStopTimingsByBusRouteId(busRouteId);
        if(timings == null || timings.isEmpty()) {
            showMessage("No timings found.");
            return;
        }

        StopTiming timing = timings.get(0);

        System.out.println();

        for(Map.Entry<String, String> entry : timing.getStopTimings().entrySet()) {
            String stopName = entry.getKey();
            String arrivalTime = entry.getValue();
            System.out.println(stopName + " -> " + arrivalTime);
        }

        String stopName;
        while(true) {
            System.out.print("Enter stop name to update : ");
            stopName = scanner.nextLine().trim();
            if(timing.getStopTimings().containsKey(stopName)) break;
            showMessage("Stop not found.");
        }

        String newTime;
        while(true) {
            System.out.print("Enter new time (HH:MM) : ");
            newTime = scanner.nextLine().trim();
            try {
                LocalTime.parse(newTime, TIME_FORMATTER);
                break;
            } catch(DateTimeParseException e) {
                showMessage("Invalid time format.");
            }
        }

        boolean isUpdated = stopTimingManageModel.updateStopTiming(busRouteId, stopName, newTime);
        if(isUpdated) showMessage("Timing has been updated.");
    }

    void addStopTiming() {
        int busRouteId;
        while(true) {
            busRouteId = InputUtil.getChoice("Enter bus-route id : ");
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

    void viewStopTimingsByBusRouteId() {
        int busRouteId = InputUtil.getChoice("Enter bus-route id : ");
        ArrayList<StopTiming> timings = stopTimingManageModel.getStopTimingsByBusRouteId(busRouteId);
        if(timings == null || timings.isEmpty()) {
            showMessage("No timings found.");
            return;
        }

        for(StopTiming timing : timings) {
            System.out.println("--------------------------------------------------");
            System.out.printf("%-20s %-15s%n", "Stop name", "Arrival time");
            System.out.println("--------------------------------------------------");

            for(Map.Entry<String,String> entry : timing.getStopTimings().entrySet()) {
                String stopName = entry.getKey();
                String arrivalTime = entry.getValue();
                System.out.printf("%-20s %-15s%n", stopName, arrivalTime);
            }

            System.out.println("--------------------------------------------------");
        }
    }

    void showMessage(String message) {
        System.out.println(message);
    }
}


