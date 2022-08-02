package cn.jjx.coding.springboot.mockito.java8.vo;

public class Person {

    private JobPosition jobPosition;
    private String name;

    public Person(String name) {
        this.name=name;
    }

    public Person(){}

    public void setCurrentJobPosition(JobPosition jobPosition) {
    }

    public String getName() {
        return name;
    }
}
