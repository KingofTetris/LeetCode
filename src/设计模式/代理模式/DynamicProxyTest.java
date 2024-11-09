package 设计模式.代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * @author by KingOfTetris
 * @date 2023/8/15
 */

interface GameFactory{
    void produceGame();
}

//被代理类
class CAPCOM implements GameFactory{
    @Override
    public void produceGame() {
        System.out.println("正在制造街霸6");
    }
}

class FromSoftware implements GameFactory{
    @Override
    public void produceGame() {
        System.out.println("正在制造艾尔登法环");
    }
}


/**
 * 动态代理需要解决的2个问题
 * 1.如何根据运行时的目标对象，创造被代理类及其对象？
 * 2.通过代理类对象调用方法时，如何动态去调用目标对象的同名方法？
 */


/**
 * 所以创建动态代理类，只要是两个方法
 *
 * 1.Proxy.newInstance 获取被代理类对象
 *
 *  MyInvocationHandler handler = new MyInvocationHandler();
 *         handler.bind(obj);//绑定被代理对象
 * Proxy.newInstance(obj.getClass().getClassLoader(),
 *                 obj.getClass().getInterfaces(),
 *                 handler);
 *
 *  2. 对方法同名方法进行增强，也就是自定义一个类实现InvocationHandler接口
 *
 *  在invoke(Object proxy, Method method, Object[] args)中实现具体的
 *  增强逻辑。
 *
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        CAPCOM capcom = new CAPCOM();
        FromSoftware fs = new FromSoftware();
        ArrayList<GameFactory> objs = new ArrayList<>();
        objs.add(capcom);
        objs.add(fs);

        //动态代理 一个代理类适配多个目标对象。
        /**
         * 当然实际上你是并不知道他实现了GameFactory接口的，这个obj属于哪个类型也是可以通过反射获取的。
         */
        for (Object obj : objs) {
            GameFactory gameFactory = (GameFactory) DynamicProxy.getProxyInstance(obj);//创建被代理类对象
            gameFactory.produceGame();
            System.out.println();
        }

    }
}

//动态代理类
class DynamicProxy{
    //调用此方法返回一个动态代理类对象解决问题1
    public static Object getProxyInstance(Object obj){ //obj 目标被代理对象
        //3个参数
        /**
         * 1.类加载器，保持和目标对象一致
         * 2.目标对象的接口 和被代理类实现一致的接口
         * 3.
         **/
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);//绑定被代理对象

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                handler);
    }
}

class MyInvocationHandler implements InvocationHandler{

    //这个invoke方法就体现了动态的性质
    //当你通过代理类对象调用方法a()的时候，就会通过下面的invoke方法进行增强

    private Object obj;

    public void bind(Object obj){
        this.obj = obj;
    }
    /**
     * proxy就是代理对象，method就是要调用的方法，args是参数列表
     * 所以你需要传入一个目标对象进来
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //那么显然，在这个方法的前后你可以做一些通用的操作，进行方法增强，比如记录日志，访问量+1之列
        System.out.println("记录日志1");
        System.out.println(method + "方法被调用次数 + 1");
        //调用被代理类的方法。
        Object returnValue = method.invoke(obj, args);
        System.out.println("记录日志2");
        return returnValue;

    }
}


