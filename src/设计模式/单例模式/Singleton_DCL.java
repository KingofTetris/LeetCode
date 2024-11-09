package 设计模式.单例模式;

public class Singleton_DCL {

    //DCL模式 你需要把instance改为volatile，保证实例的可见性和禁止指令重排序。
    private volatile static Singleton_DCL instance;

    // 私有构造函数，防止外部实例化
    private Singleton_DCL() {
    }

    // 全局访问点
    public static Singleton_DCL getInstance() {
        if (instance == null) { //第一次检查 实例是否为null
            //如果为null 去抢类模板🔒。
            synchronized (Singleton_DCL.class) {
                //抢到的再进行一次判断，如果instance不为null就没有必要去创建了。
                if (instance == null) {
                    //报黄是因为new 对应的Class中 只包含了私有方法，静态变量，静态方法，
                    // idea认为你没有必要去new 出来一个对象，所以进行了提示。
                    instance = new Singleton_DCL();
                }
            }
        }
        return instance;
    }
}
