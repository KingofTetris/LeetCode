package 设计模式.代理模式;

/**
 * @author by KingOfTetris
 * @date 2023/8/15
 */

/**
 * 在使用静态代理实现该功能之后，我们发现实现起来很简单，
 * 通过一个代理类就可以在不影响目标对象的前提进行扩展使用。
 * 但是我们也发现一个问题，如果我们不确定需要代理某个真实类的时候会比较麻烦，而且在类过多的时候，
 * 目标对象与代理对象都要维护，会使系统复杂度提升，维护起来也更加麻烦。
 * 不过这时我们就可以使用动态代理来进行解决。
 */

public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理对象
        NikeClothFactory nike = new NikeClothFactory();
        //创建代理对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nike);
        //增强被代理方法
        proxyClothFactory.produceCloth();
    }
}

interface ClothFactory{
    void produceCloth();
}

//代理类
class ProxyClothFactory implements ClothFactory{
    ClothFactory clothFactory;
    //把要被代理的对象放进来。
    public ProxyClothFactory(ClothFactory clothFactory) {
        this.clothFactory = clothFactory;
    }
    @Override
    public void produceCloth() {
        System.out.println("正在进行准备工作");
        //执行目标对象的方法
        clothFactory.produceCloth();
        System.out.println("正在进行收尾工作");
    }
}

//被代理类
class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("生产了一批Nike服装");
    }
}

