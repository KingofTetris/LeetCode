package JAVA基础_反射;

import org.junit.Test;

import java.util.Random;

/**
 * @author KingofTetris
 * @File newInstanceTest
 * @Time 2021/10/20  18:17
 */

//创建运行时类的实例
public class newInstanceTest {
    @Test
    public void test() throws Exception{

        String classpath = "";
        for (int i = 0; i < 50; i++) {
            int num = new Random().nextInt(3);//取0,1,2三个整数的任意一个。
            switch (num){
                case 0:
                    classpath = "java.lang.Object";
                    break;
                case 1:
                    classpath = "java.util.Date";
                    break;
                case 2:
                    classpath = "JAVA基础_反射.Person";
                    break;
            }
            Object instance = getInstance(classpath);
            System.out.println(instance);
        }
    }

    public Object getInstance(String classpath) throws Exception{
        //newInstance每次都会先调用空参构造器
        Object obj = Class.forName(classpath).newInstance();
        return obj;
    }
}
