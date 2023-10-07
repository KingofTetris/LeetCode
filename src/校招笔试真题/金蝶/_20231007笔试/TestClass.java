package 校招笔试真题.金蝶._20231007笔试;

/**
 * @author by KingOfTetris
 * @date 2023/10/7
 */
public class TestClass {
    static void testMethod(){
        System.out.println("test");
    }
    static String str = "nochange";
    public static void main(String[] args) {
        //只能有一个public类，内部类也不能是public
        /*public class Test{
        }*/
//        ((TestClass)null).testMethod();
        TestClass c = new TestClass();
        c.change(str);
        System.out.println(str);
    }
    //即使你传入的str,修改形参str的值，原本的str是不会变的
    //说明并不是引用传递。槽
    public void change(String str){
        str = "change";
    }
}
