package 一些面试常考的Java基础.atguigu.java;


//变量名a+instanceof+类名A 用于检测对象a是不是A类的实例 对A的父类是一样的结果。
//一般避免在向下转型时出现ClassCastException异常，所以先进行判断。返回true就强转，false就终止
//注意 = 号 子类赋给父类可以，父类赋给子类不行，所以想让父类赋给子类就是向下强转。 即在前面加上  A a1 = (A)a
public class Instanceof关键字 {
    /*public static void main(String[] args){
        Keys k = new Keys();
        son  s = new son();//这里会报错
        if(k instanceof Keys){
            System.out.println(" k is Keys");
        }
        if(s instanceof Keys){
            System.out.println(" s is Keys");
        }
    }*/
}

class son extends Keys implements Comparable{
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public son(String name, int age){
        this.age = age;
        this.name = name;
    }


    //重写toString
    @Override
    public String toString() {
        return "son{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
    /**
     * 重写compareTo的规则
     * 如果当前对象this大于obj 返回个正整数
     * 如果当前对象this小于obj 返回个负整数
     * 如果当前对象this等于obj 返回个0
     * **/
    @Override
    public int compareTo(Object o) {
        if (o instanceof son){
            if (this.age > ((son) o).age)
                return 1;
            else if (this.age < ((son) o).age)
                return -1;
            else return 0;
        }
        throw new RuntimeException("输入的类型不对");
    }
}
