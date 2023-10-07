package 校招笔试真题.金蝶._20231007笔试;

/**
 * @author by KingOfTetris
 * @date 2023/10/7
 */
public class 父类的同步方法子类能重写吗 {
    public static void main(String[] args) {
        Son son = new Son();
        son.A();
    }
}


class Father{

    Father(){
        System.out.println("父类的构造方法");
    }
    public synchronized void A(){
        System.out.println("这是父类的构造方法");
    }
}
class Son extends Father{

    Son(){
//        super(); 子类的构造方法其实在第一行隐藏了一个super显示调用父类的构造方法
        System.out.println("子类的构造方法");
//        super(); super只能写在第一行!
    }
    @Override
    public synchronized void A() {
        System.out.println("子类重写的父类的同步方法");
    }
}
