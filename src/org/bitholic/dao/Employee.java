package org.bitholic.dao;

/**
 * Created by bitholic on 16/7/11.
 */
public class Employee {
    private String eid;
    private String name;
    private String eshift;
    private String department;
    private String egroup;
    private String address;
    private Double longitude;
    private Double latitude;

    public Employee() { }

    public Employee(String eid, String name, String eshift, String department, String egroup, String address, Double longitude, Double latitude) {
        this.eid = eid;
        this.name = name;
        this.eshift = eshift;
        this.department = department;
        this.egroup = egroup;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEgroup() {
        return egroup;
    }

    public void setEgroup(String egroup) {
        this.egroup = egroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEshift() {
        return eshift;
    }

    public void setEshift(String eshift) {
        this.eshift = eshift;
    }
}
