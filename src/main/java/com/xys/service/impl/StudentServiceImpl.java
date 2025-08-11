package com.xys.service.impl;

import com.xys.entity.Student;
import com.xys.mapper.StudentMapper;
import com.xys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {

        return studentMapper.findAll();
    }
}