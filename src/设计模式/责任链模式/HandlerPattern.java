package 设计模式.责任链模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/8
 */

//责任链模式将请求和处理分割，请求不需要知道谁去处理，处理者也不需要知道请求的全貌。
    //可以提高系统灵活性，你新增一个处理器到系统中，也不会有很大影响。
    //但是缺点也很明显，有的请求直接找最终处理器就行了，但他必须层层审核才能到最后。
// 必须从链头走到链尾，效率低下。并且不利于调试。因为你很可能不知道现在是谁在处理这个请求。
public class HandlerPattern {

    public static void main(String[] args) {
        Handler level1 = new Leader();
        Handler level2 = new Boss();
        level1.setNextHandler(level2);
        int info = 11;
//        int info = 5;
        level1.process(info);
    }

}

abstract class Handler{
    //protected传递给子类
    protected Handler nextHandler;
    public void setNextHandler(Handler nextHandler){
        this.nextHandler = nextHandler;
    }

    //处理请求
    public abstract void process(Integer info);
}

class Leader extends Handler{

    @Override
    public void process(Integer info) {
        if (info > 0 && info < 10){
            System.out.println("Leader 处理请求");
        }
        else
            nextHandler.process(info);
    }
}

class Boss extends Handler{

    @Override
    public void process(Integer info) {
        System.out.println("Boss 处理请求");
    }
}
