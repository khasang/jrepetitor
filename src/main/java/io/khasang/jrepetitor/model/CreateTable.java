package io.khasang.jrepetitor.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreateTable {

    private JdbcTemplate jdbcTemplate;

    public CreateTable() {
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String createTableStatus(){
        try{
            jdbcTemplate.execute("DROP TABLE IF EXISTS cats;");
            jdbcTemplate.execute("CREATE TABLE cats\n" +
                    "(\n" +
                    "  ID  SERIAL PRIMARY KEY,\n" +
                    "  NAME character varying(255),\n" +
                    "  COLOR integer,\n" +
                    "  DESCRIPTION character varying\n" +
                    ")\n" +
                    "WITH (\n" +
                    "  OIDS=FALSE\n" +
                    ");\n" +
                    "ALTER TABLE cats\n" +
                    "  OWNER TO postgres;\n");
            return "Table created";
        }
        catch (Exception e){
            return "Create table failes";
        }
    }


    public String truncateTableStatus() {
        try {
            jdbcTemplate.execute("TRUNCATE TABLE cats;");
            return "Table truncated";
        } catch (DataAccessException e) {
            return "Truncate failes";
        }
    }
}
