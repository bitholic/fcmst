package org.bitholic.dao;

/**
 * Created by bitholic on 16/7/13.
 */
public class Station {
    private Integer stationID;
    private Double longitude;
    private Double latitude;
    private String stationName;

    public Station() { }

    public Station(Double latitude, Integer stationID, Double longitude, String stationName) {
        this.latitude = latitude;
        this.stationID = stationID;
        this.longitude = longitude;
        this.stationName = stationName;
    }

    public Integer getStationID() {
        return stationID;
    }

    public void setStationID(Integer stationID) {
        this.stationID = stationID;
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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
