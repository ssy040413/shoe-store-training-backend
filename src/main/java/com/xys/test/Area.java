package com.xys.test;

import org.springframework.stereotype.Component;

@Component
public class Area {
    public int sum() {return 1+2+3+4+5+6+7+8+9+10;}
    public double sanjiaoxingmianji(double a,double b,double c)
    {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
    public double zhengfangxingmianji(double a)
    {
        return a * a;
    }
    public double tixingmianji(double a,double b,double h)
    {
        return (a + b) * h / 2.0;
    }

}
