package org.bitholic.dao;

import java.util.List;

/**
 * Created by bitholic on 16/7/13.
 */
public class DriverJson {
    private Integer state;
    private Long sum;
    private List<Driver> drivers;

    public DriverJson() { }

    public DriverJson(Integer state, Long sum, List<Driver> drivers) {
        this.state = state;
        this.sum = sum;
        this.drivers = drivers;
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

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
