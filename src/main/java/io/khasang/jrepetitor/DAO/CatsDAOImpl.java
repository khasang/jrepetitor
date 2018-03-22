package io.khasang.jrepetitor.DAO;

import io.khasang.jrepetitor.model.Cat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class CatsDAOImpl implements CatsDAO {

    private Logger logger = LoggerFactory.getLogger(CatsDAO.class);

    private JdbcTemplate jdbcTemplate;

    public CatsDAOImpl() {
    }

    public CatsDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String insert(Cat cat) {
        String sql="INSERT INTO cats (name, color,description) VALUES (?, ?,?);";
        try {
            int i = jdbcTemplate.update(sql,cat.getName(),cat.getColor(),cat.getDescription());
            logger.info(jdbcTemplate.toString());
            return i+"rows inserted";
        } catch (DataAccessException e) {
            return "insert fails "+e.getMessage();
        }
    }

    @Override
    public String update(Cat cat) {
        String sql="UPDATE cats SET color=?, description=? WHERE name = ?;";
        try {
            int i = jdbcTemplate.update(sql,cat.getColor(),cat.getDescription(),cat.getName());
            return i+"rows updated";
        } catch (DataAccessException e) {
            return "update fails "+e.getMessage();
        }
    }

    @Override
    public String delete(Cat cat) {
        String sql="DELETE FROM cats WHERE name = ?;";
        try {
            int i = jdbcTemplate.update(sql,cat.getName());
            return i+"rows deleted";
        } catch (DataAccessException e) {
            return "delete fails "+e.getMessage();
        }
    }
}
