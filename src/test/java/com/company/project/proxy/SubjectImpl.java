package com.company.project.proxy;

/**
 * Created by dewey.du
 * Date on 2020-9-28 15:23:54
 */
public class SubjectImpl implements Subject{
    @Override
    public String sayHello(String name) {
        return name + ": hello!";
    }

    @Override
    public String sayBye(String name) {
        return name + ": good bye!";
    }
}
