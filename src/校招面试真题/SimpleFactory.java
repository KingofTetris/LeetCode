package 校招面试真题;


/**
 * 简单工厂模式的一个简单实现，
 * 比如一个接口Product，分别由A和B两个子类去实现这两个接口。实现各自不同的逻辑
 * 然后再来一个工厂类。Factory根据传入的参数去执行两个子类实现的不同方法。
 */
public class SimpleFactory {
    public static void main(String[] args) {
        // 使用工厂创建产品
        Product productA = Factory.createProduct("A");
        Product productB = Factory.createProduct("B");
        // 调用产品的方法
        productA.method();
        productB.method();
    }
}

// 产品接口
interface Product {
    void method();
}

// 具体产品A
class ProductA implements Product {
    @Override
    public void method() {
        System.out.println("A method");
    }
}

// 具体产品B
class ProductB implements Product {
    @Override
    public void method() {
        System.out.println("B method");
    }
}

// 简单工厂

/**
 * 简单工厂模式通过工厂来封装产品的创建过程，客户端只需通过工厂来获取产品实例，而无需直接实例化具体产品。
 * 这样可以将对象的创建和使用解耦，提供了一种简单的扩展方式，当需要新增产品时，只需修改工厂的创建逻辑即可。
 */
class Factory {
    // 根据传入的参数创建不同的产品
    public static Product createProduct(String type) {
        if (type.equals("A")) {
            return new ProductA();
        } else if (type.equals("B")) {
            return new ProductB();
        }
        throw new IllegalArgumentException("Invalid product type: " + type);
    }
}
