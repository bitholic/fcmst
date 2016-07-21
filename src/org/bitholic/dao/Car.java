package org.bitholic.dao;

import java.util.Date;

/**
 * Created by bitholic on 16/7/6.
 */
public class Car {
    //注意: id并没有映射数据库column
    private Integer id;

    //数据库colomn
    private String licensePlate;
    private String trademark;
    private Integer seat;
    private String registerDate;
    private String insuranceDate;
    private String vehicleLicense;
    private Integer state;

    public Car() {
    }

    public Car(String licensePlate, Integer seat, String trademark, String registerDate, String insuranceDate, String vehicleLicense, Integer state) {
        this.licensePlate = licensePlate;
        this.seat = seat;
        this.trademark = trademark;
        this.registerDate = registerDate;
        this.insuranceDate = insuranceDate;
        this.vehicleLicense = vehicleLicense;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(String insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
