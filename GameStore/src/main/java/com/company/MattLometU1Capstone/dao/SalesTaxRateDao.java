package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.SalesTaxRate;

import java.util.List;

public interface SalesTaxRateDao {
    SalesTaxRate getSalesTaxRate(String state);
    List<SalesTaxRate> getAllSalesTaxRates();
}
