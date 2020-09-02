package com.company.MattLometU1Capstone.ViewModel;


import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class GameViewModel {


    private Integer game_id;

    @Size(min = 1, max = 50, message = "Title must be between 1-50 characters")
    private String title;
    @Size(min = 1, max = 50, message = "Esrb rating must be between 1-50 characters")
    private String esrb_rating;
    @Size(min = 1, max = 255, message = "Description must be between 1-255 characters")
    private String description;
    @DecimalMin(value = "0.01", message = "Lowest price is 0.01") @DecimalMax(value = "999.99", message = "Max price is $999.99")
    private BigDecimal price;
    @Size(min = 1, max = 50, message = "Studio must be between 1-50 characters")
    private String studio;
    @Min(value = 1)@Max(value = 1000000,message = "Quantity cannot be negative")
    private Integer quantity;

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrb_rating() {
        return esrb_rating;
    }

    public void setEsrb_rating(String esrb_rating) {
        this.esrb_rating = esrb_rating;
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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
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
        GameViewModel that = (GameViewModel) o;
        return Objects.equals(getGame_id(), that.getGame_id()) &&
                getTitle().equals(that.getTitle()) &&
                getEsrb_rating().equals(that.getEsrb_rating()) &&
                getDescription().equals(that.getDescription()) &&
                getPrice().equals(that.getPrice()) &&
                getStudio().equals(that.getStudio()) &&
                getQuantity().equals(that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(game_id, title, esrb_rating, description, price, studio, quantity);
    }

    @Override
    public String toString() {
        return "GameViewModel{" +
                "game_id=" + game_id +
                ", title='" + title + '\'' +
                ", esrb_rating='" + esrb_rating + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
