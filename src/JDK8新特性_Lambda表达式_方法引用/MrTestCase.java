package JDK8新特性_Lambda表达式_方法引用;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by KingOfTetris
 * @date 2024/10/19
 */
public class MrTestCase {

    @Test
    public void case1() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Sam", 20));
        personList.add(new Person("Lily", 23));
        personList.add(new Person("Jack", 20));
        //传统写法，Collections排序，使用Comparator这个函数式接口
        //因为Comparator点进去看好像一大堆东西，其实其它都default方法，或者已经实现的方法
        //你要做的就是实现compare方法。
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Person.compare(o1, o2);
            }
        });
        //lambda写法
        Collections.sort(personList, (o1, o2) -> {
            return Person.compare(o1, o2);
        });

        //方法引用，因为我们实际上就只需要使用Person的compare方法
        //两个参数都省了。
        //但其实我们Person类里面还有一个三个参数的compare方法
        //但是方法引用会根据参数个数，类型自动推导来选择适合的方法。
        Collections.sort(personList,Person::compare);

        System.out.println(personList);
    }


    //方法引用配合stream，调用类的方法
    @Test
    public void case2(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Sam", 20));
        personList.add(new Person("Lily", 23));
        personList.add(new Person("Jack", 20));

        //通常方法引用需要配合stream进行使用
        //结果也是一样。
        personList.stream()
                .sorted(Person::compare)
                .forEach(System.out::println);
        //和builder模式差不多，都是一步一步搭建。在上一步的结果上继续处理。
    }

    //方法引用配合stream，调用对象的方法
    @Test
    public void case3(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Sam", 20));
        personList.add(new Person("Lily", 23));
        personList.add(new Person("Jack", 20));
        Person p = new Person("zhangsan",25);
        //通常方法引用需要配合stream进行使用
        //结果也是一样。
        personList.stream()
                .sorted(Person::compare)
                .forEach(p::concat);
        //和builder模式差不多，都是一步一步搭建。在上一步的结果上继续处理。
    }

    //stream流转集合
    @Test
    public void case4(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Sam", 20));
        personList.add(new Person("Lily", 23));
        personList.add(new Person("Jack", 20));
        Person p = new Person("laoliu",27);
        personList.add(p);
        List<Person> collect = personList.stream()
                .sorted(Person::compare)
                .collect(Collectors.toList());
        System.out.println(collect);

    }
}
