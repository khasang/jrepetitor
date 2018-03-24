package io.khasang.jrepetitor.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CreateTable {
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CreateTable.class);

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CreateTable() {

    }

    public  String createTableStatus()
    {
            String query = "Select name from cats where name = ?";
            return jdbcTemplate.execute(query, new PreparedStatementCallback<String>()
            {
                @Override
                public String doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                    preparedStatement.setString(1,"Barsik");
                    logger.info(preparedStatement.toString());
                    return String.valueOf(preparedStatement.execute());
                }
            });




    }
}
