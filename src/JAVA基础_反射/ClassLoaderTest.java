package JAVA基础_反射;

import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author KingofTetris
 * @File ClassLoaderTest
 * @Time 2021/10/20  17:04
 */
public class ClassLoaderTest {
    @Test
    public void test(){
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();

        System.out.println(classLoader);
        ClassLoader classLoaderParent = classLoader.getParent();
        System.out.println(classLoaderParent);

        ClassLoader loaderParentParent = classLoaderParent.getParent();
        System.out.println(loaderParentParent);

        System.out.println(String.class.getClassLoader());
    }

    /**
     * Properties:用于生成或读取配置文件
     */
    @Test
    public void test2() throws Exception {


        //properties 实际上是一张map
        //因为Properties实际上是Hashtable的子类
        Properties properties = new Properties();

        //IDEA此时的文件 默认在当前的module(day11)下
        //如果是eclipse就是当前项目下

        //读取配置文件的方式1 使用文件流FileInputStream
//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        properties.load(fis);

        //方式2 使用ClassLoader
        //配置文件默认是在module的src下 当然指定一下路径也可以 注意java里的路径是 \\
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("jdbc.properties");
        properties.load(inputStream);

        /**
         * 前面说到properties实际上就张map，所有在这里才有 get(key) 的操作
         */
        String user = properties.getProperty("user");
        String pwd = properties.getProperty("password");

        System.out.println("user:"+ user);
        System.out.println("pwd:"+ pwd);

    }
}
