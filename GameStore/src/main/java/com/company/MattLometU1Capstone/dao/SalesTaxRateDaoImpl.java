package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SalesTaxRateDaoImpl implements SalesTaxRateDao {

    private JdbcTemplate jdbcTemplate;


    private static final String SELECT_SALESTAXRATE_SQL =
            "select * from sales_tax_rate where state = ?";

    private static final String SELECT_ALL_SALESTAXRATE_SQL =
            "select * from sales_tax_rate";


    @Autowired
    public SalesTaxRateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SalesTaxRate getSalesTaxRate(String state) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SALESTAXRATE_SQL, this::mapRowToSalesTaxRate, state);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("You must enter a valid state");
        }
    }

    @Override
    public List<SalesTaxRate> getAllSalesTaxRates() {

        return jdbcTemplate.query(SELECT_ALL_SALESTAXRATE_SQL, this::mapRowToSalesTaxRate);
    }


    private SalesTaxRate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException {
        SalesTaxRate SalesTaxRate = new SalesTaxRate();
        SalesTaxRate.setState(rs.getString("state"));
        SalesTaxRate.setRate(rs.getBigDecimal("rate"));

        return SalesTaxRate;
    }
}
