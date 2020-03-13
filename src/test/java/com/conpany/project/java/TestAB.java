package com.conpany.project.java;

/**
 * Created by deweydu
 * Date on 2020/3/13 14:09
 */
public class TestAB implements InterfaceAB{
    @Override
    public double divide(int x, int y) {
        return 0;
    }

    @Override
    public int add(int x, int y) {
        return 0;
    }

    @Override
    public String encryt(byte[] result) {
        return null;
    }

    @Override
    public int minus(int x, int y) {
        return 0;
    }

    @Override
    public String decryt(byte[] result) {
        return null;
    }

    @Override
    public int get(int num) {
        System.out.println(num);
        return num;
    }

    public static void main(String[] args) {
        TestAB ab = new TestAB();
        ab.get(2);
    }
}
