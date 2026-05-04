package com.swetha.transitly.data.dto;

public class BusRoute {
    private int busId;
    private int routeId;
    private int busRouteId;
    private String shift;

    public BusRoute( int busRouteId, int busId, int routeId, String shift) {
        this.busRouteId = busRouteId;
        this.busId = busId;
        this.routeId = routeId;
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(int busRouteId) {
        this.busRouteId = busRouteId;
    }
}
