package com.xys.mapper;

import com.xys.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> findAll();
}