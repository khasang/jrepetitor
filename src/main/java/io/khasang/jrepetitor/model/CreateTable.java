package io.khasang.jrepetitor.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class CreateTable {
    private static final Logger log = LoggerFactory.getLogger(CreateTable.class);
    private JdbcTemplate jdbcTemplate;

    public CreateTable() {
    }

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String createTableStatus() {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS cats");
            jdbcTemplate.execute("CREATE TABLE public.cats\n" +
                    "(\n" +
                    " id integer NOT NULL,\n" +
                    " name character varying(255),\n" +
                    " description character varying,\n" +
                    " cat_color integer,\n" +
                    " CONSTRAINT \"Cat_pkey\" PRIMARY KEY (id)\n" +
                    ")");
            return "table created";
        } catch (Exception e) {
            return "Table creation failed " + e;
        }
    }
}
