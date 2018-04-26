package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.StudentDao;
import io.khasang.jrepetitor.dto.StudentDTO;
import io.khasang.jrepetitor.entity.Student;
import io.khasang.jrepetitor.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> getAllStudents() {
        return null;
    }
}
