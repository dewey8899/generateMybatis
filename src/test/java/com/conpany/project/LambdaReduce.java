package com.conpany.project;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by deweydu
 * Date on 2020/3/16 14:39
 */
public class LambdaReduce {
    public static void main(String[] args) {
        List<User> userList = Lists.newArrayList();
        User user1 = new User();
        user1.setName("张三");
        user1.setMoney(new BigDecimal("21.00"));
        User user2 = new User();
        user2.setName("李四");
        user2.setMoney(new BigDecimal("22.00"));
        User user3 = new User();
        user3.setName("李四3");
        user3.setMoney(new BigDecimal("23.00"));
        userList.add(user1);
        userList.add(user2);
        List<BigDecimal> collect = userList.stream().map(User::getMoney).collect(Collectors.toList());
        collect.stream().reduce((v1,v2)->{
            System.out.println(v1);
            v1 =BigDecimalUtils.add(v1, v2);
            System.out.println(v1);
            return v2;
        });
    }
}
@Data
class User{
    private BigDecimal money;
    private String name;
}
