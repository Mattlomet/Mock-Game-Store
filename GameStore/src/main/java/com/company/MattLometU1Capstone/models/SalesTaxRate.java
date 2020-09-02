package com.company.MattLometU1Capstone.models;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class SalesTaxRate {

    @Size(min = 1,max = 2,message = "State must be 2 characters")
    private String state;
    @DecimalMin(value = "0.01", message = "Lowest rate is 0.01") @DecimalMax(value = "9.99", message = "Max rate is $9.99")
    private BigDecimal rate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTaxRate that = (SalesTaxRate) o;
        return state.equals(that.state) &&
                rate.equals(that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }

    @Override
    public String toString() {
        return "SalesTaxRate{" +
                "state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }
}
