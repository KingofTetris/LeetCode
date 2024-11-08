package 设计模式.抽象工厂模式;


/**
 * @author by KingOfTetris
 * @date 2024/11/7
 */
public class Test {

    //现在从手机工厂，变成了北京，广州工厂，不止生产手机，还可以生产口罩等等。
    //可以发现抽象工厂其实工厂模式的一个扩展，如果他只生产一类产品，那么他和工厂方法模式没有什么区别。

    //但是如果你要进行扩展，还是要对修改抽象工厂和具体工厂的代码
    //也就是下面的AbstractFactory和SuperFactory
    //比如你再加一个createToy，就还需要一套逻辑去完成
    public static void main(String[] args) {
        AbstractFactory factory = new SuperFactory();
        Mask N95 = factory.createMask("N95");
        Phone iphone = factory.createPhone("IPhone");
        //可以发现这个超级工厂，什么都可以造，调用者不需要知道内部创建的细节，只要传入一个类型参数即可。
        Phone huaWeiPhone = factory.createPhone("HuaWeiPhone");
        Mask kn90 = factory.createMask("KN90");
    }
}

interface AbstractFactory {
    Phone createPhone(String param);

    Mask createMask(String param);
}

class SuperFactory implements AbstractFactory {
    @Override
    public Phone createPhone(String param) {
        if ("IPhone".equals(param)) return new IPhone();
        if ("HuaWeiPhone".equals(param)) return new IPhone();
        return null;
    }

    @Override
    public Mask createMask(String param) {
        if ("N95".equals(param)) return new N95();
        if ("KN90".equals(param)) return new KN90();
        return null;
    }
}

interface Phone {
}

class IPhone implements Phone {
}

class HuaWeiPhone implements Phone {
}

interface Mask {
}

class N95 implements Mask {
}

class KN90 implements Mask {
}
