package com.company.MattLometU1Capstone.models;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class ProcessingFee {
    @Size(min = 1, max = 20, message = "Product type must be between 1-20 character")
    private String product_type;
    @DecimalMin(value = "0.01", message = "Lowest fee is 0.01") @DecimalMax(value = "99.99", message = "Max fee is $99.99")
    private BigDecimal fee;

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee that = (ProcessingFee) o;
        return product_type.equals(that.product_type) &&
                fee.equals(that.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_type, fee);
    }

    @Override
    public String toString() {
        return "ProcessingFee{" +
                "product_type='" + product_type + '\'' +
                ", fee=" + fee +
                '}';
    }
}
