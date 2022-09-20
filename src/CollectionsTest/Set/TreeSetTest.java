package CollectionsTest.Set;

import JAVA基础_反射.Person;
import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author KingofTetris
 * @File TreeSetTest
 * @Time 2021/10/31  17:05
 *TreeSet的底层数据结构是红黑树，特点：唯一且有序，
 *  TreeSet集合会自动对元素进行排序，默认是升序排序。
 *
 * 1.向TreeSet添加对象，必须是同一类
 * 2.这个类要实现排序方式之一：自然排序（实现Comparable接口下的compareTo方法） 和 定制排序（Comparator）
 * 3.TreeSet中：比较两个对象是否相同的标准是：compareTo()或者compare()的返回值是不是0，是0就一样，不是就不一样
 * 不再是hashCode()和equals()。
 * 4.TreeSet的初始化中如果加上comparator参数，就按参数的排序方式来排序 定制排序 即使你的Person自己实现了CompareTo
 *  还是按照参数里的comparator的compare方法来排序
 */
public class TreeSetTest {
    @Test
    public void test(){
        TreeSet set = new TreeSet();
        set.add(123);
        set.add(123);
        //TreeSet可以按照类的指定属性来进行排序，言外之意就是要同一类的对象才能添加到TreeMap中
        set.add(132);
//        set.add("AA"); java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String

        for (Object o : set){
            System.out.println(o);
        }
    }

    @Test
    public void test2(){
        TreeSet set = new TreeSet();

        //两种排序方式：自然排序和定制排序
        set.add(new Person("Tom",22));
        set.add(new Person("Tom",21));

        //父子类实际上都不允许通过，只能是同一类
//        set.add(new Student("Tom",12));
        for (Object o : set){
            System.out.println(o);
        }
    }

    @Test
    public void test3(){

        Comparator com = new Comparator() {  //定制Comparator类

            //还是一样的排序方式
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;
                    if (!p1.getName().equals(p2.getName())){
                        return p1.getName().compareTo(p2.getName());
                    }
                    else
                        return -(p1.age - p2.age);
                }
                else{
                    throw new RuntimeException("输入参数不匹配");
                }
            }
        };

        //如果构造函数加上了Comparator参数，就按参数的排序方式来排序 定制排序 即使你的Person自己实现了CompareTo
        //还是按照参数里的comparator的compare方法来排序
        TreeSet set = new TreeSet(com);
        set.add(new Person("Tom",22));
        set.add(new Person("Jerry",22));
        set.add(new Person("Tom",23));
        set.add(new Person("John",82));

        for (Object o : set){
            System.out.println(o);
        }

    }
}
