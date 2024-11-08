package 设计模式.工厂方法模式;

import java.util.Collections;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/11/7
 */
public class FactoryMethod {

    public static void main(String[] args) {
        //Collections就是一个经典的工厂模式。
        //现在每生产一种产品就去造出一个新的工厂，而不是把新产品都耦合在一个工厂里面
        //比如Collections下面有LinekdList,ArrayList两种工厂。
        //但他的问题是只能创造 一个 “大类”产品，因为这些工厂都实现了同一个接口，或者继承自某个类
        //比如都只能生产手机。
        List emptyList = Collections.EMPTY_LIST;

        //华为工厂生产华为手机
        Factory factory = new HuaWeiFactory();
        Phone phone = factory.createPhone();
        phone.print();
    }
}

interface Phone {

    void print();
}

class IPhone implements Phone {

    @Override
    public void print() {
        System.out.println("IPhone");
    }
}

class HuaWeiPhone implements Phone {

    @Override
    public void print() {
        System.out.println("HuaWeiPhone");
    }
}

//工厂
interface Factory {
    Phone createPhone();
}

class IPhoneFactory implements Factory {

    @Override
    public Phone createPhone() {
        return new IPhone();
    }
}

class HuaWeiFactory implements Factory {

    @Override
    public Phone createPhone() {
        return new HuaWeiPhone();
    }
}
