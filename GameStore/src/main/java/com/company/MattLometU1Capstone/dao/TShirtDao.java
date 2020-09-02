package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.TShirt;

import java.util.List;

public interface TShirtDao {
    TShirt addTShirt(TShirt tShirt);
    TShirt getTShirt(int id);
    List<TShirt> getAllTShirts();
    void updateTShirt(TShirt tShirt);
    void deleteTShirt(int id);
    List<TShirt> getTShirtByColor(String color);
    List<TShirt> getTShirtBySize(String size);
}
