package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.StudentDTO;
import io.khasang.jrepetitor.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
}
