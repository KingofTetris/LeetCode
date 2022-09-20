package JAVA基础_反射;

import java.util.Objects;

/**
 * @author KingofTetris
 * @File Student
 * @Time 2021/10/20  20:53
 */
public class Student extends Person{
    public String name;
    public int age;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student integers = (Student) o;
        return age == integers.age && Objects.equals(name, integers.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, age);
    }

    public Student(String name, int age, String name1, int age1) {
        super(name, age);
        this.name = name1;
        this.age = age1;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
