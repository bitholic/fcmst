package org.bitholic.dao;

/**
 * Created by bitholic on 16/7/13.
 */
public class Driver {
    private String driverLicense;
    private String name;
    private String driverID;

    public Driver() { }

    public Driver(String driverLicense, String name, String driverID) {
        this.driverLicense = driverLicense;
        this.name = name;
        this.driverID = driverID;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }
}
