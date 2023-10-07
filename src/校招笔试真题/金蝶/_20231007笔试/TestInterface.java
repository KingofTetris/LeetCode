package 校招笔试真题.金蝶._20231007笔试;

/**
 * @author by KingOfTetris
 * @date 2023/10/7
 */

//甚至接口本身其实也是默认被abstract修饰的。
public abstract interface TestInterface {
//    final void AA();
    public void BB();

    //不能使用final protected修饰接口中的方法
//    protected void CC();

    //接口中的方法默认是public abstract的
    //private是可以的，但是必须要写上方法体。
    private  void DD(){
//        System.out.println("sadsa");
    }
    abstract void EE();
}
