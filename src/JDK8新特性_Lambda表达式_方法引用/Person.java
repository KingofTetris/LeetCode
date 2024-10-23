package JDK8新特性_Lambda表达式_方法引用;

/**
 * @author by KingOfTetris
 * @date 2024/10/19
 */
public class Person {

    private String name;

    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public static int compare(Person a, Person b) {
        int r = a.getAge().compareTo(b.getAge());
        if (r != 0) {
            return r;
        } else {
            return a.getName().compareTo(b.getName());
        }
    }

    public static int compare(Person a, Person b, Person c) {
        return 0;
    }

    public Person concat(Person b) {
        this.setName(this.getName() + "," + b.getName());
        System.out.println(this);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
