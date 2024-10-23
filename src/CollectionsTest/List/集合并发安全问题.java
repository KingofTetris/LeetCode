package CollectionsTest.List;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author by KingOfTetris
 * @date 2024/10/19
 */
public class 集合并发安全问题 {

    //for与迭代器，并发安全问题

    public static final List<String> list = new ArrayList<>();

    static {
        list.add("java");
        list.add("mysql");
        list.add("redis");
        list.add("spring");
        list.add("linux");
        list.add("git");
    }


    //for循环
    @Test
    public void testRemoveFor() {
        list.add(2, "redis");
        System.out.println(list);
        //用for循环遍历集合，i和list.size() 都在变化
        //会导致意想不到的事情发生
        //直接就跳过了某些元素
        //所以下面IDEA还会给你报黄Suspicious ‘List.remove()’ in loop
        //说明这个删除是可疑的。是不安全的不可靠的。
        for (int i = 0; i < list.size(); i++) {
            if ("redis".equals(list.get(i))) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    //增加for循环
    @Test
    /**
     * 这种方式就直接报错了：java.util.ConcurrentModificationException
     *
     * 将 .class 文件反编译后以上代码对应如下（将 .class 用 IDEA 打开即可）：
     *
     * Iterator var1 = list.iterator();
     * while(var1.hasNext()) {
     * 	String str = (String)var1.next();
     * 	if ("redis".equals(str)) {
     * 		list.remove(str);
     *        }
     * }
     *
     * 你会发现foreach实际上就是迭代器来遍历。
     *
     * 当在 for-each 中通过 java.util.ArrayList.remove 删除元素时，
     * 迭代器内部其实是不知道的，所以在执行 java.util.ArrayList.Itr#next() 操作时，
     * 会检查内部状态（modCount）是否一致，不一致则抛出。java.util.ConcurrentModificationException
     */
    public void testRemoveForEach() {
        for (String str : list) {
            if ("redis".equals(str)) {
                list.remove(str);
            }
        }
        System.out.println(list);
    }

    //方式三：迭代器

    /**
     * 这种方式与上面 testRemoveForEach 相比，
     * 其实就只是将内部的 java.util.ArrayList#remove 换成了迭代器内部的 remove 方法，
     * 既然用的是迭代器自己的，那迭代器内部肯定就能够知道列表发生了变化，从而直接更新其内部状态，所以就不会报错了。
     */
    @Test
    public void testRemoveIterator() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if ("redis".equals(str)) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

//    方式五：removeIf
//    如果使用的是 JDK 1.8 及以上，建议使用这种方式删除元素，代码简洁优雅。
//    查看源码，removeIf 底层其实就是使用迭代器的方式进行删除。
    @Test
    public void testRemoveIf() {
        list.removeIf("redis"::equals);
        System.out.println(list);
    }

    /*default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }*/

/*————————————————

    版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

    原文链接：https://blog.csdn.net/weixin_47429870/article/details/139203070*/
}
