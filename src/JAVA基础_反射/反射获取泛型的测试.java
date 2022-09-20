package JAVA基础_反射;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author KingofTetris
 * @File 反射获取泛型的测试
 * @Time 2021/10/20  20:56
 */
public class 反射获取泛型的测试 {

    @Test
    public void test(){
        Class<Person> personClass = Person.class;

        //获取父类
        Type genericSuperclass = personClass.getGenericSuperclass();
        System.out.println(genericSuperclass);

        //转化成ParameterizedType 是为了用paramType.getActualTypeArguments()
        //才能拿到<T>里面的 T
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        //getActualTypeArguments()返回的是一个参数列表
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        for(Type t:actualTypeArguments){
            //最后要记得getTypeName 就拿到classpath了。
            //这个时候我们就能用class.forName来创建运行时对象了。
            System.out.println(t.getTypeName());
        }
    }


    /**
     * 获取运行时类实现的接口  动态代理会用
     * 动态代理就是不指定代理类到底是谁
     * 等到运行时根据被代理类的需要来创建代理类
     * 这就是这个测试的实际应用
     *
     * Person实现了List和Comparable接口
     */
    @Test
    public void test1(){
        Class<Person> personClass = Person.class;
        Class<?>[] interfaces = personClass.getInterfaces();
        for (Class c:interfaces){
            System.out.println(c);
        }
    }


    /**
     * 获取运行时类的注解 框架中最常用的就是这个 当然也可以是Method,Field等到上面的注解
     * 因为我们总结过 注释如果没有反射来解析，那只是个标记而已。
     */

    @Test
    public void test3(){
        Class<Person> personClass = Person.class;
        Annotation[] annotations = personClass.getAnnotations();
        for(Annotation a:annotations){
            System.out.println(a);
        }
    }
}
