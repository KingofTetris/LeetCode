package 校招笔试真题.锐捷网络;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
class A{
    static {
        System.out.print("A");
    }
    A(){
        System.out.print("a");
    }
}

class B extends A{
    static {
        System.out.print("B");
    }
    B(){
        System.out.print("b");
    }
}

public class Test1 {
    public static void main(String[] args) {
        new B();
    }
}
