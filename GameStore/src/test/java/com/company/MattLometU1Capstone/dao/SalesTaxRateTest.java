package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.SalesTaxRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesTaxRateTest {

    @Autowired
    SalesTaxRateDao salesTaxRateDao;

    @Test
    public void getGetGetAllSalesTaxRates(){
        List<SalesTaxRate> salesTaxRateList = salesTaxRateDao.getAllSalesTaxRates();
        SalesTaxRate salesTaxRate = salesTaxRateList.get(0);

        assertEquals(salesTaxRate, salesTaxRateDao.getAllSalesTaxRates().get(0));

        assertEquals(salesTaxRate, salesTaxRateDao.getSalesTaxRate(salesTaxRate.getState()));
        System.out.println(salesTaxRateList);
    }
}
