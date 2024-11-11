package CollectionsTest.Map;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * 这版本的idea enter class name 快捷键是ctrl n
 * @author KingofTetris
 * @File MapTest
 * @Time 2021/11/1  15:34
 *
 *  /---Map:双列数据key-value，底层的key是用set来存储的，而value由于可重复，但无序。
 *  所以只能说是collection，这样key-value就凑成了一个entry（项目），也是用set来存储entry
 *
 *  因此又涉及到HashSet的比较，所以Map中存储对象也要重写hashCode()和equals()方法。
 *  先比较hashCode()然后比较equals() 如果hashcode都不一致，肯定不一样，如果hashcode一样再通过equals方法比较
 *  *      /---{@link java.util.HashMap}:现在的主要实现类，线程不安全，key-value都可以存储null
 *          /---{@link java.util.LinkedHashMap}:保证遍历map数据时，按照输入顺序输出，
 *          原因和LinkedHashSet一样， 是在底层添加了两个引用，频繁遍历可以用LinkedHashMap
 *
 *      /---{@link java.util.Hashtable}:作为古老的实现类，线程安全，key-value均不能存null
 *          /---{@link java.util.Properties}:常用来处理配置文件，key-value都是string
 *      /---{@link java.util.TreeMap}:类似TreeSet。和TreeSet基本类似，不过按Key排序（自然排序和定制排序）。
 *
 *
 *      HashMap的底层：数组+链表 jdk7 1.7底层结构是一个存储 Entry<K,V>[] table的数组
 *                    数组+链表+红黑树 jdk8 1.8则是 Node<K,V>[] table。
 *                    存储的结构从Entry变成了Node 就是为了变化到红黑树
 *                    而且 static class Node<K,V> implements Map.Entry<K,V>  是实现关系
 *      底层实现原理，以jdk7为例:
 *      HashMap map = new HashMap();
 *      在实例化后，底层创建去了一个长度16的Entry[] table;
 *      ...可能多次put后...
 *      map.put(key1,value1)
 *      实际上又回到了HashSet的底层实现 数组+链表
 *      首先通过key1的hashCode()获取key1的hashCode，然后通过相应的哈希函数计算出应当插入的数组索引值index
 *      如果index上为空，直接插入key1-value1即可 --情况1
 *      如果index上已有元素，(存在一个或多个元素以链表形式相连)，比较key1的hashCode和已经存在元素key的hashCode
 *          如果key1的哈希值与其他已经存在的元素的key的哈希值都不相同，添加成功 --情况2
 *          如果key1的哈希值和已经存在的某个元素(key2-value2)的哈希值相同。调用key1所在类的equals()方法
 *              //其实真要说的话，还不是equals 而是先对比 key1和 key2的地址 地址一样还对比个毛的equals。
 *              如果key1.getClass().equals(key2)返回false,插入成功--情况3
 *              如果key1.getClass().equals(key2)返回true:使用value1覆盖value2。
 *                因为我们说过map是映射关系，单值函数。不可能让一个key对应多个value
 *
 *      扩容：当超出threshold(扩容临界值)= MAXIMIUM_CAPACITY(一定是2^n) * 0.75（loadfactor) 为什么是0.75?
 *      这个0.75是由泊松分布算出来的。
 *      加载因子过高，例如为1，虽然减少了空间开销（链表多，数组短），提高了空间利用率，但同时也增加了查询时间成本；
 *      加载因子过低，例如0.5，虽然可以减少查询时间成本（链表少，数组长），但是空间利用率很低，同时提高了resize操作的次数。
 *      在设置初始容量时应该考虑到映射中所需的条目数及其加载因子，以便最大限度地减少resize操作次数，所以，一般在使用HashMap时建议根据预估值设置初始容量，减少扩容操作。
 *      选择0.75作为默认的加载因子，完全是时间和空间成本上寻求的一种折衷选择
 *      且要存入的index非空时，才会考虑去扩容，扩容后要重算hashCode，因为你的数组长度变了。
 *      index也会跟着变。如果不扩容，添加元素时候是用的头插法，新元素总是在头部。
 *      默认扩容是两倍，把原来的数据哈希重算过来
 *
 *
 *      jdk8的实现略有不同：底层不是Entry[] table了，而是Node[]，而且不是初始化时建立而是put的时候才建立
 *      当某个索引位置的链表长度 >= 8（TREEIFY_THRESHOLD） 且数组长度 >=64(MIN_TREEIFY_CAPACITY)的时候，
 *      此位置的链表就转换成红黑树存储。目的是为了提高查找效率
 *      要注意如果红黑树的结点个数小于 8 了，那会再次转化会链表
 *
 *      LinkedHashMap的不同在于重写了newNode方法。使用两个Entry<K,V>before和after来记录前后顺序。所以才叫Linked
 *
 *      看完HashMap 再看HashSet 实际上 HashSet就是把集合里的值放在key的位置，value是个PRESENT，
 *      这个PRESENT没啥实际意义，只是防止你为空。不过它是单例的所有KEY的value都是指向它，不用重复在堆里占用空间
 *
 *      TreeMap唯一要说的就是按照key排序,不能按照value排序。实现和TreeSet一样，没啥好说的。
 */
public class MapTest {
    @Test
    public void test(){
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(null,null);
        //编译不报错，运行时空指针异常
//        Hashtable hashtable = new Hashtable();
//        hashtable.put(null,null);
    }


    @Test
    public void test2()  {
        Properties properties = new Properties();

        try {
            FileInputStream fileInputStream = new FileInputStream("jdbc.properties");
            properties.load(fileInputStream);
            System.out.println(properties.getProperty("user"));
            System.out.println(properties.getProperty("password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
