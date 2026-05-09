package com.swetha.transitly.data.dto;

public class Bus {
    private final int busId;
    private final String busNumber;
    private final String busName;

    public Bus(int busId, String busNumber, String busName) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.busName = busName;
    }

    public int getBusId() {
        return busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getBusName() {
        return busName;
    }
}
