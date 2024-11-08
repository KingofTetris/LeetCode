package 设计模式.简单工厂模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/7
 */

/**
 *  优点：实现了对象创建和对象使用的分离。使用者不需要知道他是怎么创建的，交给工厂生成，然后拿去直接用就行了。解耦合
 *  缺点：简单工厂类不够灵活，每新增一种产品，就需要修改工厂类。那么可想而知如果产品越多，那么整个工厂的方法就会非常复杂
 *  违反了开闭原则，无法灵活扩展。
 *
 *  简单工厂的例子就比如 DateFormat 的get方法，你传入一种类型，他返回一种产品。
 */
public class SimpleFactory {
    public static Product createProduct(String type){
        if ("A".equals(type)){
            return new A();
        }
        if ("B".equals(type)){
            return new B();
        }
        return null;
    }

    public static void main(String[] args) {
        Product product = SimpleFactory.createProduct("A");
        product.produce();
    }
}

abstract class Product {
    public abstract void produce();
}

class A extends Product{

    @Override
    public void produce() {
        System.out.println("A");
    }
}

class B extends Product{
    @Override
    public void produce() {
        System.out.println("B");
    }
}


