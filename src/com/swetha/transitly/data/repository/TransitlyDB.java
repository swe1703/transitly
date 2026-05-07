package com.swetha.transitly.data.repository;

import com.swetha.transitly.data.dto.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TransitlyDB {
    private TransitlyDB() {
    }

    private static TransitlyDB transitlyDB = null;

    public static final TransitlyDB getInstance() {
        if(transitlyDB == null) transitlyDB = new TransitlyDB();
        return transitlyDB;
    }

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Bus> buses = new ArrayList<>();
    private ArrayList<Route> routes = new ArrayList<>();
    private ArrayList<BusRoute> busAndRoutes = new ArrayList<>();
    private ArrayList<StopTiming> stopTimings = new ArrayList<>();

    private final String adminEmail = "transitly@gmail.com";
    private final String adminPassword = "transitly@1703";

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public User getUserByEmail(String email) {
        if(email == null) return null;

        for(User user : users) {
            if(user.getEmail().equalsIgnoreCase(email.trim())) {
                return user;
            }
        }

        return null;
    }

    public User addUser(User user) {
        if(user == null) return null;

        users.add(user);
        return user;
    }

    public Bus addBus(Bus bus) {
        buses.add(bus);
        return bus;
    }

    public ArrayList<Bus> getAllBuses() {
        return buses;
    }

    public Route addRoute(Route route) {
        routes.add(route);
        return route;
    }

    public ArrayList<Route> getAllRoutes() {
        return routes;
    }

    public Route getRouteById(int routeId) {
        for(Route route : routes) {
            if(route.getRouteId() == routeId) {
                return route;
            }
        }

        return null;
    }

    public BusRoute getBusRouteById(int busRouteId) {
        for(BusRoute busRoute : busAndRoutes) {
            if(busRoute.getBusRouteId() == busRouteId) return busRoute;
        }

        return null;
    }

    public StopTiming setStopTiming(StopTiming stopTiming) {
        stopTimings.add(stopTiming);
        return stopTiming;
    }

    public ArrayList<BusRoute> getAllBusRoutes() {
        return busAndRoutes;
    }

    public Bus getBusById(int busId) {
        for(Bus bus : buses) {
            if(bus.getBusId() == busId) return bus;
        }

        return null;
    }

    public BusRoute addBusRoute(BusRoute busRoute) {
        busAndRoutes.add(busRoute);
        return busRoute;
    }

    public ArrayList<StopTiming> getTimingsByBusRouteId(int busRouteId) {
        ArrayList<StopTiming> result = new ArrayList<>();
        for(StopTiming timing : stopTimings) {
            if(timing.getBusRouteId() == busRouteId) result.add(timing);
        }

        return result;
    }

    public boolean removeBus(int busId) {
        for(Bus bus : buses) {
            if(bus.getBusId() == busId) {
                buses.remove(bus);
                return true;
            }
        }

        return false;
    }

    public boolean removeRoute(int routeId) {
        for(Route route : routes) {
            if(route.getRouteId() == routeId) {
                routes.remove(route);
                return true;
            }
        }

        return false;
    }

    public boolean removeBusRoute(int busRouteId) {
        for(BusRoute busRoute : busAndRoutes) {
            if(busRoute.getBusRouteId() == busRouteId) {
                busAndRoutes.remove(busRoute);
                return true;
            }
        }

        return false;
    }

    public boolean updateStopTiming(int busRouteId, String stopName, String newTime) {
        for(StopTiming stopTiming : stopTimings) {
            if(stopTiming.getBusRouteId() == busRouteId) {
                LinkedHashMap<String, String> timings = stopTiming.getStopTimings();

                if(timings.containsKey(stopName)) {
                    timings.put(stopName, newTime);
                    return true;
                }
            }
        }

        return false;
    }
}
