package org.bitholic.dao;

import java.util.List;

/**
 * Created by bitholic on 16/7/13.
 */
public class StationJson {
    private Integer state;
    private Long sum;
    private List<Station> stations;

    public StationJson() { }

    public StationJson(Integer state, Long sum, List<Station> stations) {
        this.state = state;
        this.sum = sum;
        this.stations = stations;
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

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
