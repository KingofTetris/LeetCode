package JAVA基础_反射;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author KingofTetris
 * @File ReflectionTest
 * @Time 2021/10/20  15:35
 */
public class ReflectionTest {
    @Test
    public void test1() throws Exception{

        //普通方式是不能直接调用私有属性和私有方法的。比如private show和private 的 name
        //只能通过get set方法访问
        Person p = new Person("Tom",12);
        System.out.println(p);
        p.show();
        p.setName("Jerry");
        p.setAge(12);

        System.out.println(p);
        System.out.println("*********************************************");

        /**
         * 通过反射可以调用运行时类的构造器，方法，属性 反射的强大就在于私有也可以直接调用*/

        Class personClass = Person.class;

        //获得public 的构造方法
        Constructor cons = personClass.getConstructor(String.class, int.class);

        /**获得private 一定要用getDeclaredConstructor declared声明的方法都是获取
         当前运行时类的所有属性 方法 构造器，不区分private 还是public
         不加上Declared是取不到私有构造方法的。 另外私有构造方法还得设置可达性为true
         不加Declared 的 getConstructor 获取的是运行时类及其父类的public的构造方法
         */
        Constructor cons1 = personClass.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        /**
         * 调用newInstance创建对应运行时类的对象
         * 要想这个方法正常运行
         * 1.得有空参构造器
         * 2.访问权限通常是public
         *
         * 这也是JavaBean为什么要求有一个空参构造器 权限为Public的原因
         * 1.就是为了反射创建运行类的准备的
         * 2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器   因为你创建了有参的 就不会去自动调用空参了
         */
        Object john = cons.newInstance("John", 18);
        Object hepa = cons1.newInstance("Hepa");

        System.out.println(john);
        System.out.println(hepa);

        //调用属性
        //获得私有属性name
        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(john,"pahe");
        System.out.println(john);

        //调用方法
        //获得public方法 show
        Method method = personClass.getDeclaredMethod("show");
        method.setAccessible(true);
        //获得private show
        Method method1 = personClass.getDeclaredMethod("show",String.class);
        method1.setAccessible(true);

        method.invoke(john);

        Object nation = method1.invoke(john, "中国");

        System.out.println(nation);

    }


    /**
     * 通过new和反射都可以造对象，那要用哪个？
     * 建议使用new的方式，什么时候使用反射？当你不知道要new什么对象的时候（动态性）
     *
     *
     * 反射机制和封装性是不是矛盾的？如何看待这两项技术
     * 举个例子就是反射破坏了单例模式。可以再反射出一个新的对象
     * 相当于有public的大门可以走，你非得翻private的窗户。
     * 有了反射以后，private public更多的是一种标识作用。最好用public的 private就不要用了
     *
     * 关于java.lang.Class的理解
     * 1.类的加载过程
     * java命令对生成的.class文件解释运行，
     * 相当于将某个字节码文件加载到内存中，并将这些静态数据转化成方法区的运行时数据结构
     * 这个过程就是类的加载。
     * 这些加载到内存中的类 就是运行时类，作为Class的一个实例
     * 也就是说Class的实例就是一个运行时类
     * 实际上不仅仅是类，interface,数组,enum,Annotation,基本数据类型,void都是Class的实例
     *
     * 2.加载到内存中的运行时类，会缓存一段时间，在此时间类，我们可以用下面常用的三种方式
     * 获取运行时类。经过JVM GC机制会回收这些Class对象
     * */


    //获取Class的方式
    @Test
    public void test3() throws Exception {
        //法一 调用运行时类的属性 .class
        Class<Person> personClass = Person.class;

        //法二 用实例的getClass方法
        Person p1 = new Person();
        Class<? extends Person> p1Class = p1.getClass();

        //法三 调用Class的静态方法，forName(String classpath) 这种常用
        Class<?> forName = Class.forName("JAVA基础_反射.Person");

        System.out.println(forName == p1Class);
        System.out.println(personClass == p1Class);
        System.out.println(personClass == forName);
        //所以三种方法都是指向同一个地址


        //法四 类加载器，ClassLoader 了解即可
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        classLoader.loadClass("JAVA基础_反射.Person");
    }

    @Test
    public void test4(){
        int[] b = new int[10];
        int[] a = new int[100];
        //数组有点特殊，只要维度和类型都一样，就是一样的class。长度是无所谓的
        System.out.println((a.getClass() == b.getClass()));
    }
}
