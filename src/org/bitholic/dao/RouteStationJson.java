package org.bitholic.dao;

import java.util.List;

/**
 * Created by bitholic on 16/7/13.
 */
public class RouteStationJson {
    private Integer state;
    private Long sum;
    private List<RouteStation> routeStations;

    public RouteStationJson() { }

    public RouteStationJson(Integer state, Long sum, List<RouteStation> routeStations) {
        this.state = state;
        this.sum = sum;
        this.routeStations = routeStations;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public List<RouteStation> getRouteStations() {
        return routeStations;
    }

    public void setRouteStations(List<RouteStation> routeStations) {
        this.routeStations = routeStations;
    }
}
