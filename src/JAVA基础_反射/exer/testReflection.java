package JAVA基础_反射.exer;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author by KingOfTetris
 * @date 2023/4/26
 */
public class testReflection {

    @Test
    public void test() throws Exception {

        //通过全类名加载类
//        String fullName = "JAVA基础_反射.exer.Apple";
        //一般全类名是写在配置文件中的
        /**
         * 读取全类名
         */
        Properties properties = new Properties();
        File file = new File("src/config.properties");//File默认从module开始,所以要加上src/
        FileInputStream fis = new FileInputStream(file);
        properties.load(fis);
        String fullName = properties.getProperty("fruitName");
        /**
         * 开始通过反射构造水果
         * PS. Class.forName的底层有三个动作，类加载，链接，初始化
         * 类加载把class文件读入内存，放入方法区
         * 链接暂时不要求，如若想了解，请看JVM
         * 初始化会执行方法 clinit
         * 完成所有类变量(类中被static修饰的变量)的赋值，和静态代码块的运行
         *
         * 对应的ClassLoader则会延迟初始化的时间。这就是forName和ClassLoader的差别
         *
         */
        Class clazz = Class.forName(fullName);
        //先得到一个构造器，无参getDeclaredConstructor就不加参数
        //有参构造器就加上对应的参数即可
        Constructor cons = clazz.getDeclaredConstructor();
        cons.setAccessible(true);//如果是private的就加上可达
        //然后通过构造器实例化
        Fruit fruit  = (Fruit) cons.newInstance();//因为已经知道业务范围就是Fruit，直接强转了
        //现在有了水果通过榨汁机去榨汁
        Juicer juicer = new Juicer();
        juicer.run(fruit);
//        //当然有了Clazz你还可以去使用对应类的所有属性和方法 因为测试里水果只有一个方法，所以就不用了。
//        Field field = clazz.getDeclaredField("name");
//        field.setAccessible(true);
//        field.set(fruit,"苹果");
//        Method squezz = clazz.getDeclaredMethod("squezz");//getDeclaredMethod参数是类型的方法名，
//        squezz.setAccessible(true);
//        squezz.invoke(fruit);
        // 后面可能还有方法的参数类型，因为squzz是空参的，所以就写了个方法名

    }
}
