package cn.jjx.coding.springboot.test;

import java.util.*;

public class Test {
    static List<String>  list = new ArrayList<>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        generate(8);
        for(String ss:list){
            System.out.println(ss);
        }
        System.out.println(list.size());
    }

    public static List<String> generate(int num){
        generate(0,0,num);
        return list;
    }

    public static void generate(int left,int right,int num){
        if(left==num&&right==num){
            list.add(stringBuilder.toString());
        }
        if(left<right){
            return;
        }
        if(left<num){
            stringBuilder.append('(');
            generate(left+1,right,num);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        if(left>right&&right<num){
            stringBuilder.append(')');
            generate(left,right+1,num);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

    }

}
