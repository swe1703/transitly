package com.swetha.transitly.data.dto;

import java.util.HashMap;

public class Schedule {
    private int scheduleId;
    private int busId;
    private int routeId;
    private HashMap<String, String> stopTimings;

    public Schedule() {
    }

    public Schedule(int scheduleId, int busId, int routeId, HashMap<String, String> stopTimings) {
        this.scheduleId = scheduleId;
        this.busId = busId;
        this.routeId = routeId;
        this.stopTimings = stopTimings;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
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

    public HashMap<String, String> getStopTimings() {
        return stopTimings;
    }

    public void setStopTimings(HashMap<String, String> stopTimings) {
        this.stopTimings = stopTimings;
    }
}
