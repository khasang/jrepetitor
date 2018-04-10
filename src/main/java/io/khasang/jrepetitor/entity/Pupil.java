package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Column(columnDefinition = "DATE")
    private LocalDate year;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "carList")
    private List<Teacher> employeeList = new ArrayList<>();

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

    public List<Teacher> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Teacher> employeeList) {
        this.employeeList = employeeList;
    }
}
