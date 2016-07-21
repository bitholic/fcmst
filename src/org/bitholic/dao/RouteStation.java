package org.bitholic.dao;

/**
 * Created by bitholic on 16/7/13.
 */
public class RouteStation {
    private Integer id;
    private Integer stationID;
    private String routeName;
    private Integer sequence;

    public RouteStation() { }

    public RouteStation(Integer id, Integer stationID, String routeName, Integer sequence) {
        this.id = id;
        this.stationID = stationID;
        this.routeName = routeName;
        this.sequence = sequence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStationID() {
        return stationID;
    }

    public void setStationID(Integer stationID) {
        this.stationID = stationID;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
