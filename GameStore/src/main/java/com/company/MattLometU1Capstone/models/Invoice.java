package com.company.MattLometU1Capstone.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class Invoice {

    private Integer invoice_id;

    @Size(min = 1, max = 80, message = "Name must be between 1-80 character")
    private String name;
    @Size(min = 1, max = 30, message = "Street must be between 1-30 character")
    private String street;
    @Size(min = 1, max = 30, message = "City must be between 1-30 character")
    private String city;
    @Size(min = 1, max = 30, message = "State must be between 1-30 character")
    private String state;
    @Size(min = 1, max = 5, message = "Zip Code must be between 1-5 character")
    private String zipcode;
    @Size(min = 1, max = 20, message = "Item Type must be between 1-20 character")
    private String item_type;
    @Min(value = 0,message = "Must supply an item id")
    private Integer item_id;
    private BigDecimal unit_price;
    @Min(value = 1,message = "Must supply a quantity")
    private Integer quantity;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal processing_fee;
    private BigDecimal total;

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(BigDecimal processing_fee) {
        this.processing_fee = processing_fee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(getInvoice_id(), invoice.getInvoice_id()) &&
                getName().equals(invoice.getName()) &&
                getStreet().equals(invoice.getStreet()) &&
                getCity().equals(invoice.getCity()) &&
                getState().equals(invoice.getState()) &&
                getZipcode().equals(invoice.getZipcode()) &&
                getItem_type().equals(invoice.getItem_type()) &&
                getItem_id().equals(invoice.getItem_id()) &&
                getUnit_price().equals(invoice.getUnit_price()) &&
                getQuantity().equals(invoice.getQuantity()) &&
                Objects.equals(getSubtotal(), invoice.getSubtotal()) &&
                Objects.equals(getTax(), invoice.getTax()) &&
                Objects.equals(getProcessing_fee(), invoice.getProcessing_fee()) &&
                Objects.equals(getTotal(), invoice.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, name, street, city, state, zipcode, item_type, item_id, unit_price, quantity, subtotal, tax, processing_fee, total);
    }

    @Override
    public String  toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", item_type='" + item_type + '\'' +
                ", item_id=" + item_id +
                ", unit_price=" + unit_price +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processing_fee=" + processing_fee +
                ", total=" + total +
                '}';
    }
}
