package com.company.MattLometU1Capstone.ViewModel;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class TShirtViewModel {

    private Integer t_shirt_id;

    @Size(min = 1, max = 20, message = "Size can be between 1-20 characters")
    private String size;
    @Size(min = 1, max = 20, message = "Color can be between 1-20 characters")
    private String color;
    @Size(min = 1, max = 255, message = "Description must be between 1-255 characters")
    private String description;
    @DecimalMin(value = "0.01", message = "Lowest price is 0.01") @DecimalMax(value = "999.99", message = "Max price is $999.99")
    private BigDecimal price;
    @Min(value = 0,message = "Quantity must be positive")
    private Integer quantity;

    public Integer getT_shirt_id() {
        return t_shirt_id;
    }

    public void setT_shirt_id(Integer t_shirt_id) {
        this.t_shirt_id = t_shirt_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirtViewModel that = (TShirtViewModel) o;
        return Objects.equals(getT_shirt_id(), that.getT_shirt_id()) &&
                getSize().equals(that.getSize()) &&
                getColor().equals(that.getColor()) &&
                getDescription().equals(that.getDescription()) &&
                getPrice().equals(that.getPrice()) &&
                getQuantity().equals(that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(t_shirt_id, size, color, description, price, quantity);
    }

    @Override
    public String toString() {
        return "TShirtViewModel{" +
                "t_shirt_id=" + t_shirt_id +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
