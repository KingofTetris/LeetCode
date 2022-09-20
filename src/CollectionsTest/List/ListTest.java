package CollectionsTest.List;

import JAVA基础_反射.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author KingofTetris
 * @File ListTest
 * @Time 2021/10/31  14:54
 *
 *  /----Collection接口：单列集合，存储一个一个对象
 *          /---List接口:存储有序，可重复的对象   "动态数组"
 *              /---ArrayList:作为List的主要实现类，线程不安全，底层是Object[] elementData这样的数组存储元素，所以适合查找
 *              /---LinkedList:也是主要实现类，但底层是Node<E> 这样的双向列表。所以适合作增删操作
 *              /---Vector:作为List的古老实现类，线程安全，底层也是Object[] elementData，但现在基本被ArrayList给淘汰了。
 *
 *  实现类和Collection的区别最大就在于，Collection是没有索引的。而实现类有。
 *  像是get,indexOf,lastIndexOf,subList，remove这些方法都用到了索引
 *  要注意的是remove 你到底要删除索引还是对象 普通数字就是索引，new Integer就对象
 */


public class ListTest {


    /***
     * 遍历方法
     * 1.foreach
     * 2.迭代器
     * 3.普通循环
     */
    @Test
    public void test(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(321);
        list.add(new Person("Tom",21));

        //迭代器 用hasNext遍历
        //为什么有了foreach还要有iterator?
        //原因在于，你未必知道Collection中到底是什么类型，foreach要指定类型
        //且foreach无法跟着遍历修改collection，但迭代器可以  iterator.remove();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for(Object o :  list){
            System.out.println(o);
        }
    }
}
