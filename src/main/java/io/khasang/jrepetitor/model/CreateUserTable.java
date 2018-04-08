package io.khasang.jrepetitor.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class CreateUserTable {
    private static final Logger log = LoggerFactory.getLogger(CreateTable.class);

    private JdbcTemplate jdbcTemplate;

    public CreateUserTable() {
    }

    public CreateUserTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String createUserTableStatus() {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS new_users");

            jdbcTemplate.execute("CREATE TABLE public.new_users\n" +
                    "(\n" +
                    "  id integer NOT NULL,\n" +
                    "  login character varying(255),\n" +
                    "  name character varying(255),\n" +
                    "  password character varying(255),\n" +
                    "  role integer,\n" +
                    "  CONSTRAINT \"new_users_pkey\" PRIMARY KEY (id)\n" +
                    ")");

            jdbcTemplate.execute("INSERT INTO new_users (id, login, name, password, role) VALUES (1, 'Oleg', 'Oleg', 'Oleg', 2);\n" +
                    "INSERT INTO new_users (id, login, name, password, role) VALUES (2, 'Dima', 'Dima', 'Dima', 1);");
            return "userTable created";
        } catch (Exception e) {
            return "UserTable creation failed " + e;
        }
    }
}
