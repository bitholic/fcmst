package org.bitholic.dao;

/**
 * Created by bitholic on 16/7/13.
 */
public class Route {
    private String routeName;

    public Route() { }

    public Route(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
