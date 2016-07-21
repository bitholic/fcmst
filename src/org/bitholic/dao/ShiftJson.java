package org.bitholic.dao;

import java.util.List;

/**
 * Created by bitholic on 16/7/12.
 */
public class ShiftJson {
    private Integer state;
    private Long sum;
    private List<Shift> shifts;

    public ShiftJson() { }

    public ShiftJson(Integer state, Long sum, List<Shift> shifts) {
        this.state = state;
        this.sum = sum;
        this.shifts = shifts;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
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
