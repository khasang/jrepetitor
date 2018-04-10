package io.khasang.jrepetitor.dto;

import java.time.LocalDate;

public class PupilDTO {
    private long id;
    private String name;
    private LocalDate year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }
}
