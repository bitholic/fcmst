package org.bitholic.dao;

import java.util.List;

/**
 * Created by bitholic on 16/7/11.
 */
public class EmployeeJson {
    private Integer state;
    private Long sum;
    private List<Employee> employees;

    public EmployeeJson() { }

    public EmployeeJson(Integer state, Long sum, List<Employee> employees) {
        this.state = state;
        this.sum = sum;
        this.employees = employees;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
