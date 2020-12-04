package com.company.project.lambda;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 通过 lambda 转换list 为map 后 ，对map操作，即会改变list 的值
 * Created by dewey.du
 * Date on 2020-10-10 10:20:0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MapDemo {
    public static void main(String[] args) {
        List<Digit> list = new ArrayList<>();
        list.add(new Digit(5, "a"));
        list.add(new Digit(6, "b"));
        list.add(new Digit(7, "c"));
        list.add(new Digit(8, "d"));
        list.add(new Digit(9, "e"));
        Map<String, Digit> stringDigitMap = list.stream().collect(Collectors.toMap(Digit::getStr, Function.identity(), (v1, v2) -> v1));
        stringDigitMap.forEach((k,v)-> v.setNum(1));
        list.forEach(s-> System.out.println(s.getStr() + s.getNum()));

    }
    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Digit{
        private int num;
        private String str;
        public Digit(int num,String str){
            this.num = num;
            this.str = str;
        }
    }
}
