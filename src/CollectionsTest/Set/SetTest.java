package CollectionsTest.Set;

import JAVA基础_反射.Person;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author KingofTetris
 * @File SetList
 * @Time 2021/10/31  15:37
 *
 *    /---Collection接口的另一个实现类:
 *          /---Set接口:存储无序的，不可重复的数据
 *              /---HashSet：作为Set接口的主要实现类，线程不安全，可以存储null值
 *                  /---LinkedHashSet：是HashSet的子类，通过双向链表，使得遍历顺序为插入时的顺序
 *              /---TreeSet：可以按照添加对象的指定属性进行排序
 *
 *
 *     以HashSet为例：
 *    无序性：不过HashSet的无序性并不是真的随机的，
 *    只是底层的存储数组不是按索引顺序添加。而是通过元素的HashCode算出Index然后插入，不过这个数组是数组加链表
 *    所以你才会看见遍历的输出结果都一样。
 *
 *    不可重复性：保证添加的元素按照equals方法判断时，不能返回true。使相同元素只能添加一个
 *
 *    添加元素的过程：这就是Set保证无序，不可重复的原理
 *      我们向HashSet添加元素A时，先调用A所属类的hashCode()方法，计算A的哈希值
 *      此哈希值再经过哈希函数，得到A应该插入的index。
 *      如果该index上没有元素，直接插入A。----情况1
 *      如果有，则先对比A与其他元素的哈希值，
 *             如果不一样，则A添加成功---情况2
 *             如果一样，再比较A所属类的equals()方法，与其他元素对比。
 *              如果equals方法返回true,那A就是重复项，添加失败
 *              如果false，成功---情况3
 *
 *
 *      对于添加成功情况2和3，元素A与已经存在的元素以链表方式存储（拉链法）
 *      JDK7上，JDK8下，七上八下
 *
 *      所以实际上Set是先对比了hashCode和再比equals。
 *
 *      至于hashCode方法前，为什么要乘以31 是一个有趣的数学问题。
 *       for (Object element : a)
 *             result = 31 * result + (element == null ? 0 : element.hashCode());
 *
 * 1.所以Set里面的元素和Collection的要求基本类似，要求重写hashCode和equals方法
 * 2.怎么重写？没有牛逼到自创Java 还是直接生成就行了。直接生成的就是目前最优最合理的方法。
 */
public class SetTest {


    @Test
    public void test1() {
        Set set = new HashSet();
        set.add(123);
        set.add(2123);
        set.add(33123);
        set.add("ke123");
        set.add(123);
        set.add(new Person("Tom", 12));
        set.add(new Person("Tom", 12));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2(){

        //既然HashSet根据哈希值存放元素
        // 为什么LinkedHashSet作为HashSet的子类 就可以保证输出和输入一致呢？

        //其实LinkedHashSet在底层不仅添加了数据，还利用两个引用地址，
        // 类似双向链表记录前后顺序，这样通过链表来输出元素
        //就保证了输出顺序和输入顺序一致。而且对于频繁的遍历操作，速度就快很多。
        //也就是LinkedHashSet的遍历效率比HashSet高很多
        //但坏处就是牺牲了两个空间
        Set set = new LinkedHashSet();
        set.add(123);
        set.add(2123);
        set.add(33123);
        set.add("ke123");
        set.add(123);
        set.add(new Person("Tom",12));
        set.add(new Person("Tom",12));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }



}
