package Lambda表达式使用;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author KingofTetris
 * @File Lambda表达式的使用
 * @Time 2021/10/21  16:42
 *
 * JDK8以后的必备技能，必须要学会。
 * 使用场景 用匿名内部类的现在都可以换成lambda实现
 * Lambda表达式的使用
 * 0.要求只能是  函数式接口：接口里面只有一个抽象方法 无歧义
 * 1.举例 (o1,o2) -> Integer.compare(o1,o2)
 * 2.格式:
 *      -> :lambda操作符
 *      ->左边：lambda的形参列表 数据类型也可以省略 其实就是抽象接口中抽象方法的形参列表
 *      ->右边：lambda体  其实就是重写抽象方法的方法体
 *
 * 3.使用 六种情况
 * 4.Java中lambda的本质是对象，作为函数式接口的实例 没有接口就没有意义了
 * tips:函数式接口声明上面会有@FunctionalInterface 这个注解
 */

public class Lambda表达式的使用 {
    @Test  //语法格式一：主要看抽象方法void run() 无参，无返回值
    public void test(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱你");
            }
        };
        r1.run();
        //上面这一大段用lambda 就是空参 () -> {方法体} 一行方法体的话，可以省略{}
        Runnable r2 = () -> System.out.println("我爱你");
        r2.run();
    }

    @Test  //语法格式二：一个参数，无返回值
    public void test2(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("谎言与誓言");

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        //其实这里隐含了语法三，省略数据类型 因为编译器可以靠泛型推断出s一定是String
        Consumer<String> con1 = (String s) -> System.out.println(s);

        //语法三
        // Consumer<String> con1 = (s) -> System.out.println(s);

        //甚至是 一个参数时，()都可以省略
        Consumer<String> con2 = s -> System.out.println(s);

        con1.accept("人说谎言，剑说真相");
    }




    @Test //方法体有多条执行语句，参数有多个，有无返回值都无所谓
    public void test4(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        //就成了这样
        Comparator<Integer> com2 = (o1,o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };

    }

    //最后一种，只有一条执行语句,return 大括号都可以省去
    @Test
    public void test5(){
        Comparator<Integer> com1 = (o1,o2) -> {return o1.compareTo(o2);};

        //改成
        Comparator<Integer> com2 = (o1,o2) -> o1.compareTo(o2);

    }

}
