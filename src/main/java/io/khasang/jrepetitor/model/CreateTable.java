package io.khasang.jrepetitor.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
    private static final Logger log = LoggerFactory.getLogger(CreateTable.class);
    private JdbcTemplate  jdbcTemplate;

    public CreateTable() {
    }

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String createTableStatus() {
//        try {
//            jdbcTemplate.execute("DROP TABLE IF EXISTS cats");
//            jdbcTemplate.execute("CREATE TABLE public.cats\n" +
//                    "(\n" +
//                    "id INTEGER NOT NULL,\n" +
//                    "name CHARACTER VARYING(255),\n" +
//                    "description CHARACTER VARYING,\n" +
//                    "cat_color INTEGER,\n" +
//                    "CONSTRAINT \"Cat_pkey\" PRIMARY KEY (id)\n" +
//                    ")");
//            return "table created";
//        } catch (Exception e) {
//            return "table creation failed " + e;
//        }
        String query = "SELECT name FROM cats WHERE name = ?;";

        return jdbcTemplate.execute(query, new PreparedStatementCallback<String>() {
            @Override
            public String doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setString(1, "Barsik");
                log.info(ps.toString());
                return String.valueOf(ps.execute());
            }
        });
    }
}
