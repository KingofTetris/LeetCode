package JAVA基础_反射;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author KingofTetris
 * @File 反射获取运行时类的特定属性和方法
 * @Time 2021/10/20  21:14
 */
public class 反射获取运行时类的特定属性和方法 {
    @Test
    public void test()throws Exception{

        //这样写只是为了举例，不然下面都构造出来实例对象p了，干嘛还要大费周章去set
        //直接p.属性名 赋值就行了。因为开发中，你实际上是不知道到底是哪个类的
        Class<Person> personClass = Person.class;
        //来个对象 取它的空参构造器
        Constructor<Person> constructor = personClass.getDeclaredConstructor();
        Person p = constructor.newInstance();

        //来个属性 记得Declared啥权限的属性都能取
        //不加只能取public的 同样private
        //还是要setAccessible(true)强制取得private的东西
        Field age = personClass.getDeclaredField("age");

        //调用一下
        age.set(p,11);

        System.out.println(age.get(p));
        System.out.println(p.toString());
    }


    /**
     *实际上方法和构造器都是一样
     * 还是getDeclaredXxxxxxxxxxxxxx
     * 只不过方法需要方法名和形参列表
     */

    @Test
    public void test1() throws Exception{

    }
}
