package com.company.project.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dewey.du
 * Date on 2020-9-28 15:25:25
 */
public class InvocationHandlerImpl implements InvocationHandler {


    private Object subject;
    public InvocationHandlerImpl(Object subject){
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作
        System.out.println("在调用之前，我要干点啥呢？");
        System.out.println("Method:" + method);
        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object returnValue = method.invoke(subject, args);
        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("在调用之后，我要干点啥呢？");
        return returnValue;
    }

    public static void main(String[] args) {
        Subject realSubject = new SubjectImpl();
        InvocationHandler handler = new InvocationHandlerImpl(realSubject);
        ClassLoader classLoader = realSubject.getClass().getClassLoader();
        Class<?>[] interfaces = realSubject.getClass().getInterfaces();
        Subject subject = (Subject) Proxy.newProxyInstance(classLoader, interfaces, handler);
        System.out.println("动态代理对象的类型："+subject.getClass().getName());

        String hello = subject.sayHello("dewey.du");
        System.out.println(hello);
        String goodbye = subject.sayBye("dewey.du");
        System.out.println(goodbye);


    }
}
