package com.atguigu.java;
/*
 * 接口的使用
 * 1.使用关键字interface
 * 2.Java中类和interface是并列结构
 * 3.如何定义接口：接口中的成员
 *    3.1 JDK7前：只能定义全局常量和抽象方法
 *        全局常量：public static final 可以省略不写
 *        抽象方法:public abstract    同样可以省略
 *        JDK8后：加上静态方法和默认方法
 *4.接口中不能定义构造方法
 *5.Java中通过类去实现接口，实现C++中的多继承
 *  5.1  实现类要覆盖所有抽象方法，如果没有没有全部覆盖则仍是一个抽象类
 *6.实现接口实际上就是多加功能。
 *7.接口之间是可以继承的，而且是多继承
 *
 *
 *
 */
public class InterfaceTest {
  /*  public static void main(String[] args){

    }*/
}

interface Flyable{
    //全局常量
    public static final int MAX_SPEED = 7900;
    public static final int MIN_SPEED = 1;
    int TEST_SPEED = 3;

    public abstract void fly();

    public abstract void stop();

    void eat();

}

interface Attackable{
    void attack();
}

class Plane implements Flyable{

    @Override
    public void fly() {
        System.out.println("通过引擎起飞");
    }

    @Override
    public void stop() {
        System.out.println("着陆停止");
    }

    @Override
    public void eat() {

    }
}


//什么都不实现就加上abstract就不会报错。
abstract class bird implements Flyable{

}

class Bullet implements Flyable,Attackable{

    @Override
    public void fly() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void eat() {

    }

    @Override
    public void attack() {

    }
}