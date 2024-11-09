package 设计模式.享元模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */

//缓存、Cache、Flyweight

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式主要用于减少创建对象的数量，
 * 以减少内存占用和提高性能。这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
 * <p>
 * <p>
 * 编程中经常用的String类，数据库连接池等等。当然，享元模式主要的目的是复用，如果该对象没有的话，就会进行创建。
 * <p>
 * 享元模式的角色主要分为三大类，抽象享元类、具体享元类以及享元工厂类。
 * <p>
 * 抽象享元类：所有具体享元类的超类或者接口，通过这个接口，可以接受并作用于外部专题。
 * <p>
 * 具体享元类：实现抽象享元类接口的功能并增加存储空间。
 * <p>
 * 享元工厂类：用来创建并管理抽象享元类对象，它主要用来确保合理地共享。每当接受到一个请求是，
 * 便会提供一个已经创建的抽象享元类对象或者新建一个。 享元模式的核心在于享元工厂类，
 * 享元工厂类的作用在于提供一个用于存储享元对象的享元池，用户需要对象时，首先从享元池中获取，
 * 如果享元池中不存在 ，则创建一个新的享元对象返回给用户，并在享元池中保存该新增对象。
 * <p>
 * <p>
 * 在我们以前读书的时候，经常会用到笔，其中铅笔又是最早接触的，我们最开始使用铅笔可能不是写字，而是进行画画。
 * 这里我们可以把笔当作一个抽象享元类，
 * 铅笔当作一个具体享元类，然后再创建一个享元工厂类，用于创建和管理，最后再由调用者决定用铅笔进行干嘛。
 */
public class FlyweightPattern {

    /**
     * 极大的减少对象的创建，从而降低了系统的内存，提升了效率。
     *
     * 提高了系统的复杂度，因为需要将状态进行分离成内部和外部，
     * 并且也使外部状态固有化，使得随着内部状态的变化而变化，会造成系统的混乱。
     *
     * 使用场景：
     * 系统有大量相似对象。
     *
     * 注意事项：
     * 需要注意划分外部状态和内部状态，否则可能会引起线程安全问题。 这些类必须有一个工厂对象加以控制。
     *
     * 与单例模式比较
     *
     * 虽然它们在某些方面很像，但是实际上却是不同的东西，单例模式的目的是限制创建多个对象，
     * 避免冲突，比如使用数据库连接池。而享元模式享元模式的目的是共享，避免多次创建耗费资源，比如使用String类。
     *
     * @param args
     */
    public static void main(String[] args) {
        String names[] = {"张三", "李四", "王五", "赵六"};
        for (int i = 0; i < 8; i++) {
            Penil penil = PenFactory.get(names[i > 3 ? i - 4 : i]);
            penil.setSomething("画了一条鱼");
            penil.write();
        }
    }
}

interface Pen {
    void write();
}

class Penil implements Pen {
    private String name;
    private String something;
    private int i;

    public Penil(String name) {
        this.name = name;
        i++;
        System.out.println(name + " 第:" + i + "次创建");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }

    @Override
    public void write() {
        System.out.println(name + " 用于铅笔  " + something);
    }
}

class PenFactory {
    private static final Map<String, Penil> map = new HashMap<String, Penil>();

    public static Penil get(String name) {
        Penil penil = map.get(name);
        if (penil == null) {
            penil = new Penil(name);
            map.put(name, penil);
        }
        return penil;
    }
}
