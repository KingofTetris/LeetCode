package JAVA基础_反射;

/**
 * @author by KingOfTetris
 * @date 2023/8/15
 */

interface ClothFactory{
    void produceCloth();
}

//代理类
class ProxyClothFactory implements ClothFactory{

    ClothFactory clothFactory;

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
