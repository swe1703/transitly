package com.swetha.transitly.features.admin;

import com.swetha.transitly.data.dto.BusRoute;
import com.swetha.transitly.features.admin.busmanage.BusManageView;
import com.swetha.transitly.features.admin.busroutemanage.BusRouteManageView;
import com.swetha.transitly.features.admin.routemanage.RouteManageView;
import com.swetha.transitly.features.admin.stoptimingmanage.StopTimingManageView;
import com.swetha.transitly.util.ConsoleInput;
import java.util.Scanner;

public class AdminDashboard {
    private final Scanner scanner = ConsoleInput.getScanner();

    public void showAdminDashboard() {
        while(true) {
            System.out.println("\nAdmin Dashboard");
            System.out.println("1. Manage routes");
            System.out.println("2. Manage buses");
            System.out.println("3. Map bus to route");
            System.out.println("4. Manage stop timings");
            System.out.println("5. Logout");

            System.out.print("Enter your choice : ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice) {
                case 1 :
                    new RouteManageView().showRouteMenu();
                    break;

                case 2 :
                    new BusManageView().init();
                    break;

                case 3 :
                    new BusRouteManageView().init();
                    break;

                case 4 :
                    new StopTimingManageView().init();
                    break;

                case 5 :
                    System.out.println("Logged out successfully.");
                    return;

                default :
                    System.out.println("Invalid choice.");
            }
        }
    }
}
