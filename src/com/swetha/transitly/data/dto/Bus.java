package com.swetha.transitly.data.dto;

public class Bus {
    private int busId;
    private String busNumber;
    private String busName;

    public Bus(int busId, String busNumber, String busName) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.busName = busName;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }
}
