package 校招笔试真题.锐捷网络;

abstract class AbstractClass {
    AbstractClass() {

    }

    public abstract void abstractMethod();

    public final void finalMethod() {
        // Final method implementation
    }
}
final class AA{

}
class C {
    final void test(){
        char c = '\r';
        double d = 5.3E12;
//        byte b = 433;
//        float f = 11.1;
        float f = 11.1F;
        System.out.println("C");
    }
}
//final类无法被继承
//class B extends AA{
//
//}
class BB extends C{
    //final方法无法被重写
//    void test(){
//
//    }
}
class ConcreteClass extends AbstractClass {
    public void abstractMethod() {
        // Abstract method implementation
    }

    // 无法重写finalMethod()方法
}
