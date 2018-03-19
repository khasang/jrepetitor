package io.khasang.jrepetitor.model;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CreateTable() {

    }

    public String createTableStatus() {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS cats");
            jdbcTemplate.execute("CREATE TABLE public.cats\n" +
                    "(\n" +
                    "    id integer NOT NULL,\n" +
                    "    name character varying(255) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    description character varying COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    color integer NOT NULL,\n" +
                    "    CONSTRAINT \"Cats_pkey\" PRIMARY KEY (id)\n" +
                    ")");

            return "Table created";
        } catch (DataAccessException e) {
            return "Table creation failed " + e;
        }
    }
}
