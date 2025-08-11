package com.xys.controller;

import com.xys.entity.Teacher;
import com.xys.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/teachers") // 为教师信息接口设置一个统一的父路径
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getAllTeachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.findAll();
    }
}