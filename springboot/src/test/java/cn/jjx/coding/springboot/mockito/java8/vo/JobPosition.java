package cn.jjx.coding.springboot.mockito.java8.vo;

import java.nio.charset.Charset;

public class JobPosition {
    private String title;
    private String jobPosition;

    public JobPosition(String jobPosition) {
        this.jobPosition=jobPosition;
    }

    public JobPosition(){}


    public String getTitle() {
        return "aaa";
    }
}
