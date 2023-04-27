package JAVA基础_反射;

import 一些面试常考的Java基础.atguigu.java.MyAnnotation;

import java.util.*;

/**
 * @author KingofTetris
 * @File Person
 * @Time 2021/10/20  15:32
 */

@MyAnnotation(value = "hello")
public class Person extends Creature<Student> implements List<Integer>,Comparable{
    private String name;
    public int age;
    int id;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Person() {
        System.out.println("Person()");
    }

    private Person(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show(){
        System.out.println("我是一个人");
    }

    private String show(String nation){
        System.out.println("我的国籍是" + nation);
        return nation;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Integer get(int index) {
        return null;
    }

    @Override
    public Integer set(int index, Integer element) {
        return null;
    }

    @Override
    public void add(int index, Integer element) {

    }

    @Override
    public Integer remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Integer> listIterator(int index) {
        return null;
    }

    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        return null;
    }



    //重写规则
    @Override
    public boolean equals(Object o) {
        System.out.println("Person equals.....");
        if (this == o) return true; //如果你们的引用地址都一样那不用比了，一定一样

        if (o == null || getClass() != o.getClass()) return false;
        Person integers = (Person) o;
        return age == integers.age && id == integers.id && Objects.equals(name, integers.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, id);
    }



    //按照姓名从小到大排列，姓名一样再按照年龄从大到小排列
    @Override
    public int compareTo(Object o) {
        if (o instanceof Person){
            Person p = (Person) o;

//            按照姓名从小到大排列
            if (!this.name.equals(p.name))
                return this.name.compareTo(p.name);
            else
//                姓名一样再按照年龄从大到小排列
                return -(this.age - p.age);
        }else {
            throw new RuntimeException("输入的类型不匹配");
        }
    }
}
