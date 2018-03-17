package io.khasang.jrepetitor.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//@Repository
public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public CreateTable() {
    }

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String createTableStatus(){
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS cats");
            jdbcTemplate.execute("CREATE  TABLE public.cats (id INTEGER NOT NULL, " +
                    "name CHARACTER VARYING(255)," +
                    "description CHARACTER VARYING, " +
                    "cat_color INTEGER, " +
                    "CONSTRAINT \"Cat_pkey\" PRIMARY KEY (id))");
            return "table created!";
        } catch (Exception e){
            return "creation table failed " + e;
        }
    }
}
