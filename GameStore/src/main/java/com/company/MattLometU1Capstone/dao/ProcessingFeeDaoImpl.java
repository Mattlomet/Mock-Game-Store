package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.ProcessingFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProcessingFeeDaoImpl implements ProccessingFeeDao {

    private JdbcTemplate jdbcTemplate;


    private static final String SELECT_PROCESSING_FEE_SQL =
            "select * from processing_fee where product_type = ?";

    private static final String SELECT_ALL_PROCESSING_FEE_SQL =
            "select * from processing_fee";


    @Autowired
    public ProcessingFeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProcessingFee getProcessingFee(String processing_fee) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PROCESSING_FEE_SQL, this::mapRowToProcessingFee, processing_fee);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Please enter a valid processing fee type");
        }
    }

    @Override
    public List<ProcessingFee> getAllProcessingFees() {
        return jdbcTemplate.query(SELECT_ALL_PROCESSING_FEE_SQL, this::mapRowToProcessingFee);
    }


    private ProcessingFee mapRowToProcessingFee(ResultSet rs, int rowNum) throws SQLException {
        ProcessingFee ProcessingFee = new ProcessingFee();
        ProcessingFee.setProduct_type(rs.getString("product_type"));
        ProcessingFee.setFee(rs.getBigDecimal("fee"));

        return ProcessingFee;
    }
}
