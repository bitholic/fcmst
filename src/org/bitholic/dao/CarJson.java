package org.bitholic.dao;

import java.util.List;

/**
 * Created by bitholic on 16/7/6.
 */
public class CarJson {
    private Integer state;
    private Long sum;
    private List<Car> cars;

    public CarJson() {
    }

    public CarJson(Integer state, Long sum, List<Car> cars) {
        this.state = state;
        this.sum = sum;
        this.cars = cars;
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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
