package io.khasang.jrepetitor.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
    private static final Logger log = LoggerFactory.getLogger(CreateTable.class);
    //private static final Log log = LogFactory.getLog(CreateTable.class.getName());

    private JdbcTemplate jdbcTemplate;

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CreateTable() {

    }

//    public String createTableStatus() {
//        try {
//            jdbcTemplate.execute("DROP TABLE IF EXISTS cats");
//            jdbcTemplate.execute("CREATE TABLE public.cats\n" +
//                    "(\n" +
//                    "    id integer NOT NULL,\n" +
//                    "    name character varying(255) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
//                    "    description character varying COLLATE pg_catalog.\"default\" NOT NULL,\n" +
//                    "    color integer NOT NULL,\n" +
//                    "    CONSTRAINT \"Cats_pkey\" PRIMARY KEY (id)\n" +
//                    ")");
//            return "Table created";
//        } catch (DataAccessException e) {
//            return "Table creation failed " + e;
//        }

    public String createTableStatus() {
        String query = "SELECT name from cats Where name = ?;";
        log.info("run Create Table");
        return jdbcTemplate.execute(query, new PreparedStatementCallback<String>() {
            @Override
            public String doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, "Barsik");
                log.info(ps.toString());
                return String.valueOf(ps.execute());
            }
        });
    }


}

