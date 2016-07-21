package org.bitholic.dao;

/**
 * Created by bitholic on 16/7/12.
 */
public class Shift {
    private Integer shiftID;
    private String routeName;
    private String startTime;
    private String licensePlate;
    private String driverID;

    public Shift() { }

    public Shift(Integer shiftID, String routeName, String startTime, String licensePlate, String driverID) {
        this.shiftID = shiftID;
        this.routeName = routeName;
        this.startTime = startTime;
        this.licensePlate = licensePlate;
        this.driverID = driverID;
    }

    public Integer getShiftID() {
        return shiftID;
    }

    public void setShiftID(Integer shiftID) {
        this.shiftID = shiftID;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }
}
