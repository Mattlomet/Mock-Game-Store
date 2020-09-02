package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoImpl implements TShirtDao{

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_TShirt_SQL =
            "insert into t_shirt (size, color, description, price, quantity) values (?, ?, ?, ?, ?)";

    private static final String SELECT_TShirt_SQL =
            "select * from t_shirt where t_shirt_id = ?";

    private static final String SELECT_ALL_TShirtS_SQL =
            "select * from t_shirt";

    private static final String UPDATE_TShirt_SQL =
            "update t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ? where t_shirt_id = ?";

    private static final String DELETE_TShirt =
            "delete from t_shirt where t_shirt_id = ?";

    private static final String SELECT_TShirt_BY_COLOR =
            "select * from t_shirt where color = ?";

    private static final String SELECT_TShirt_BY_SIZE =
            "select * from t_shirt where size = ?";

    @Autowired
    public TShirtDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public TShirt addTShirt(TShirt tShirt)  {

        jdbcTemplate.update(
                INSERT_TShirt_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        tShirt.setT_shirt_id(id);
        return tShirt;
    }

    @Override
    public TShirt getTShirt(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TShirt_SQL, this::mapRowToTShirt, id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("You must enter a valid T-Shirt id");
        }
    }

    @Override
    public List<TShirt> getAllTShirts() {

        return jdbcTemplate.query(SELECT_ALL_TShirtS_SQL, this::mapRowToTShirt);
    }

    @Override
    public void updateTShirt(TShirt tShirt) {

        jdbcTemplate.update(
                UPDATE_TShirt_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity(),
                tShirt.getT_shirt_id());

    }

    @Override
    public void deleteTShirt(int id) {

        jdbcTemplate.update(DELETE_TShirt, id);

    }

    @Override
    public List<TShirt> getTShirtByColor(String color) {
        try {
            return jdbcTemplate.query(SELECT_TShirt_BY_COLOR, this::mapRowToTShirt, color);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("You must enter a valid color");
        }
    }

    @Override
    public List<TShirt> getTShirtBySize(String size) {
        try {
            return jdbcTemplate.query(SELECT_TShirt_BY_SIZE, this::mapRowToTShirt, size);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("You must enter a valid size");
        }
    }

    private TShirt mapRowToTShirt(ResultSet rs, int rowNum) throws SQLException {
        TShirt tShirt = new TShirt();
        tShirt.setT_shirt_id(rs.getInt("t_shirt_id"));
        tShirt.setSize(rs.getString("size"));
        tShirt.setColor(rs.getString("color"));
        tShirt.setDescription(rs.getString("description"));
        tShirt.setPrice(rs.getBigDecimal("price"));
        tShirt.setQuantity(rs.getInt("quantity"));
        return tShirt;
    }
}
