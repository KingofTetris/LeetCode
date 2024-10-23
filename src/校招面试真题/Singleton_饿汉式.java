package 校招面试真题;


/**
 * 饿汉式单例模式在多线程环境下是线程安全的，因为实例在类加载时就被创建，
 * 不存在并发创建实例的问题。但它可能会导致程序启动较慢，因为实例在类加载时就被创建，而不是按需创建。
 */
public class Singleton_饿汉式 {

    //饿汉式直接new出来，注意和构造方法一样都是private
    //static final直接等于一个常量，不能再次修改。
    private static final Singleton_饿汉式 instance = new Singleton_饿汉式();

    // 私有构造函数，防止外部实例化
    private Singleton_饿汉式() {
    }

    // 全局访问点,直接返回instance
    public static Singleton_饿汉式 getInstance() {
        return instance;
    }
}
