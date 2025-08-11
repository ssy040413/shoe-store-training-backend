package com.xys.controller;

import com.xys.entity.Student;
import com.xys.service.StudentService;
import com.xys.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(tags = "学生信息接口")
@RestController
@RequestMapping("/students" )

public class StudentController {

    @Autowired
    private StudentService studentService; // 这个是实例变量，首字母 s 是小写

    @ApiOperation("获取所有学生信息")
    @GetMapping("/getAllStudents")
    public ResponseResult<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        if (students != null)
            return ResponseResult.success(200, "查询成功", students);
        return ResponseResult.failure(500, "查询失败");
    }
}