package com.xys.controller;

import com.xys.test.Area;
import com.xys.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private Area area;

    @GetMapping("/homework")
    public String runhomework() {
        int a=area.sum();
        double b=area.sanjiaoxingmianji(3,4,5);
        double c=area.zhengfangxingmianji(5);
        double d=area.tixingmianji(3,4,5);
        return "1-10的和是:"+ a +"<br>"+"边长为3,4,5的三角形的面积是:"+ b +"<br>"+"边长为5的正方形的面积是:"+ c +"<br>"+"边长为3,4高为5的梯形的面积是:"+ d;
    }
}
