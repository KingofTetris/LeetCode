package Lambda表达式使用;

/**
 * @author by KingOfTetris
 * @date 2023/7/5
 */


/**
 * 为什么要用Lambda表达式？为了偷懒，现在已经变成主流了，一定要会用
 * 只能针对函数式接口 接口里面只有一个抽象方法 无歧义
 * 函数式接口
 * 概念：一个接口中的抽象方法只有一个，那么这个接口就是一个函数式接口。
 *
 * 1、通过注解检测一个接口是否是一个函数式接口：
 * @FunctionalInterface
 * 在接口上直接加上注解，如果这个接口是一个函数式接口则不报错，否则编译报错
 */

/**
 * 一开始我们写个接口，要写实现类，然后调用实现类的唯一方法
 */
@FunctionalInterface
interface IloveLambda{
    void love(int a);
}

@FunctionalInterface
interface IloveLambdaTwo{
    void love(int a,int b);
}

class LoveLambda implements IloveLambda{
    @Override
    public void love(int a) {
        System.out.println("i love lambda \t" + a);
    }
}


public class LambdaDemo {

    //静态内部类
    static class staticIloveLambda{
        public void love(int a) {
            System.out.println("i love lambda \t" + a);
        }
    }
    public static void main(String[] args) {
        //一开始 为了实现方法里面的一句话，我们是不是写了接口，实现类，然后new对象，最后调用，是不是麻烦得一笔？
        IloveLambda lambda1 = new LoveLambda();
        lambda1.love(520);
        //于是我们想用静态内部类，局部内部类来完成这个事情。
        staticIloveLambda lambda2 = new staticIloveLambda();
        lambda2.love(522);
        //局部内部类
        class classIloveLambda{
            public void love(int a) {
                System.out.println("i love lambda \t" + a);
            }
        }
        classIloveLambda lambda3 = new classIloveLambda();
        lambda3.love(523);
        //反正上面两个方法还是很繁琐，于是人们又搞出来匿名内部类，反正你只用一次，我直接new匿名就行了，懒得取名字了
        //直接new接口
        IloveLambda lambda4 = new IloveLambda() {
            @Override
            public void love(int a) {
                System.out.println("i love lambda \t" + a);
            }
        };
        lambda4.love(524);
        //但最后还是发现匿名内部类也没啥卵用。于是JDK8以后 lambda表达式诞生辣
        //因为前面的类名已经给出来了就没必要再写类名了，可以自动推导，然后因为函数式接口只有一个方法，
        // 那么也不用选了，肯定就是他
        //权限修饰符，返回值，方法名也不用写了
        IloveLambda lambda5 = (a)->{System.out.println("i love lambda \t" + a);};
        lambda5.love(525);
        //还可以简化，因为参数只有一个a,方法体只用一行，干脆()和{}都可以去掉
        IloveLambda lambda6 = a->System.out.println("i love lambda \t" + a);
        lambda6.love(526);
        //多个参数就不能省略了。
        IloveLambdaTwo lambdaTwo = (a,b)-> System.out.println("i love lambda \t" + (a+b));
        lambdaTwo.love(50,50);
    }
}

