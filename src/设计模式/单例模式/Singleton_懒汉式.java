package 设计模式.单例模式;

public class Singleton_懒汉式 {

    //初始为null，需要的时候才进行实例化
    private static Singleton_懒汉式 instance = null;

    // 私有构造函数，防止外部实例化
    private Singleton_懒汉式() {
    }

    // 全局访问点
    // 为了线程安全给getInstance加上synchronized.
    // 但是这样效率就非常慢了。所以我们需要DCL，双端检查模式。
    public static synchronized Singleton_懒汉式 getInstance() {
        //需要才去new出来。
        if (instance == null) {
            instance = new Singleton_懒汉式();
        }
        return instance;
    }
}
