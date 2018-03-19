package io.khasang.jrepetitor.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CreateTable() {

    }

    public  String createTableStatus()
    {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS cats");
            jdbcTemplate.execute("CREATE TABLE public.cats\n" +
                    "(\n" +
                    "  id integer NOT NULL,\n" +
                    "  name character varying(255),\n" +
                    "  description character varying,\n" +
                    "  cat_color integer,\n" +
                    "  CONSTRAINT \"Cat_pkey\" PRIMARY KEY (id)\n" +
                    ")\n");
            return "table created";
        }
        catch (Exception x)
        {
            return "table creation failed";
        }


    }
}
