package 校招笔试真题.金蝶._20231007笔试;

/**
 * @author by KingOfTetris
 * @date 2023/10/7
 */
public class 构造方法的嵌套调用 {
    int a;
    int b;
    构造方法的嵌套调用(int a){
        this.a = a;
    }

    构造方法的嵌套调用(int a,int b){
        //只能new 出来。不能用this。cao
        new 构造方法的嵌套调用(a);
        this.b = b;
    }
}
