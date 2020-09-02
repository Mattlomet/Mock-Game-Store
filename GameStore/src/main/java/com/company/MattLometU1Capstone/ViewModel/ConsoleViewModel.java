package com.company.MattLometU1Capstone.ViewModel;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class ConsoleViewModel {
    private Integer console_id;

    @Size(min = 1,max = 50, message = "Your model must be between 1-50 characters")
    private String model;
    @Size(min = 1,max = 50, message = "Your model must be between 1-50 characters")
    private String manufacturer;
    @Size(min = 1,max = 20, message = "Your memory amount must be between 1-20 characters")
    private String memory_amount;
    @Size(min = 1,max = 20, message = "Your processor must be between 1-20 characters")
    private String processor;
    @DecimalMin(value = "0.01", message = "Lowest price is 0.01") @DecimalMax(value = "999.99", message = "Max price is $999.99")
    private BigDecimal price;
    @Min(value = 0,message = "Quantity must be positive")
    private Integer quantity;

    public Integer getConsole_id() {
        return console_id;
    }

    public void setConsole_id(Integer console_id) {
        this.console_id = console_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemory_amount() {
        return memory_amount;
    }

    public void setMemory_amount(String memory_amount) {
        this.memory_amount = memory_amount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
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
        ConsoleViewModel that = (ConsoleViewModel) o;
        return console_id.equals(that.console_id) &&
                model.equals(that.model) &&
                manufacturer.equals(that.manufacturer) &&
                memory_amount.equals(that.memory_amount) &&
                processor.equals(that.processor) &&
                price.equals(that.price) &&
                quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(console_id, model, manufacturer, memory_amount, processor, price, quantity);
    }

    @Override
    public String toString() {
        return "ConsoleViewModel{" +
                "console_id=" + console_id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memory_amount='" + memory_amount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
