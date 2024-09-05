package JavaJunior._this和super;

/**
 * @author by KingOfTetris
 * @date 2024/9/3
 */
public class Animal{
    String name;
    int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Animal() {
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
}

class Dog extends Animal{
    private String color;

    public Dog(String color) {
        this.color = color;
    }

    public Dog(String name, int age, String color) {
        super(name, age);//构造函数中super必须放第一行
        this.color = color;
//        super(name, age);//super放在其他语句后面就会编译报错，
//        因为子类有可能使用父类属性，就必须在使用之前先对父类属性完成初始化。
        //如果你先初始化子类的属性，再调用super，就会让父类的属性覆盖掉子类的属性。
        //这就违反了继承的原则，子类替换父类，而不是父类替换子类。
    }

    public void change(){
        this.color = "yellow";
        super.name = "test";
        super.age = 12;
    }
}

