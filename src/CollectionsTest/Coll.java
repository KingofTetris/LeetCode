package CollectionsTest;

import JAVA基础_反射.Person;
import org.junit.Test;

import java.util.*;

/**
 * @author KingofTetris
 * @File Coll
 * @Time 2021/10/30  19:53
 * 实际上没什么好说的。只有一个要求
 * 当你向collection添加自定义的对象时，要重写equals方法，不然collection的很多方法都和预计不同。
 * 比如contains,containsAll,remove,removeAll.
 * 还有就是Arrays.asList后面加new int[]{11,11,22} 都只会当作一个元素对待
 */
public class Coll {
    @Test
    public void test(){
        Collection coll = new ArrayList();
        coll.add(213);
        coll.add(312);
        coll.add(new Object());
        coll.add(new Person("TOm",21));
        coll.add(coll.getClass());

        System.out.println(coll.contains(new Person("TOm", 21)));

        System.out.println(coll.remove(new Person("TOm", 21)));
        System.out.println(coll.remove(new Person("TOm", 21)));
//        for(Object o : coll){
//            System.out.println(o);
//        }
//
//        System.out.println(coll);
    }

    /**
     * Collections工具类（静态方法的一些测试）
     * Collections是一个可以对List,Map,Set进行操作的工具类
     *
     * 面试题：Collection和Collections的区别？
     *
     */

    /**
     * 一些常用的方法
     * */
    @Test
    public void test2(){


        ArrayList list = new ArrayList();
        list.add(123);
        list.add(32);
        list.add(1);
        list.add(-123);
        list.add(-99);
        list.add(0);
        list.add(0);

        //排序 自然排序 默认从小到大
        Collections.sort(list);
        System.out.println(list);
        //排序 定制排序
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return -((Integer) o1 - (Integer) o2);
            }
        });
        System.out.println(list);
        //洗牌
        Collections.shuffle(list);
        System.out.println(list);
        //反转
        Collections.reverse(list);
        System.out.println(list);
        //swap 交换
        Collections.swap(list,0,list.size()-1);
        System.out.println(list);

        //max 其实不管自然还是定制 都是返回最右边的那个元素 而min就是第一个元素
        System.out.println(Collections.max(list));

        //定制max，按这个方法
        Object max = Collections.max(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return -((Integer) o1 - (Integer) o2);
            }
        });
        System.out.println(max);


        //frequency 0在list中出现的频率
        System.out.println(Collections.frequency(list, 0));
    }

    @Test
    public void test3(){
        ArrayList src = new ArrayList();
        src.add(123);
        src.add(32);
        src.add(1);
        src.add(-123);
        src.add(-99);
        src.add(0);
        src.add(0);


        //错误写法 报异常java.lang.IndexOutOfBoundsException: Source does not fit in dest
        //因为源码里实际比较的是srcSize和destSize。
    /*    ArrayList dest = new ArrayList();
        ArrayList dest = new ArrayList(src.size());//这样写dest.size还是0
        Collections.copy(dest,src);*/

        //正确写法 注意这个是[] 不是()
        List dest = Arrays.asList(new Object[src.size()]);
        System.out.println(dest);
        Collections.copy(dest,src);
        System.out.println(dest);

    }
}
