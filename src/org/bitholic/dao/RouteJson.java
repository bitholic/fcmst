package org.bitholic.dao;

import java.util.List;

/**
 * Created by bitholic on 16/7/13.
 */
public class RouteJson {
    private Integer state;
    private Long sum;
    private List<Route> routes;

    public RouteJson() { }

    public RouteJson(Integer state, Long sum, List<Route> routes) {
        this.state = state;
        this.sum = sum;
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
