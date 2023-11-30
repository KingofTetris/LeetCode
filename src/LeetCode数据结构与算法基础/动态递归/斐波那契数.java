package LeetCode数据结构与算法基础.动态递归;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/11/30
 */

//1 1 2 3 5 8 13 ...
public class 斐波那契数 {

    @Test
    public void test(){
        int fib = fib(3);
        System.out.println(fib);
    }

    //最简单的递归
    public int fib(int n) {
        if (n == 1 || n == 2 ) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    //上面的递归树画出来就非常庞大，并且没有用中间缓存重复计算的结果，效率非常低下
    public int fibWithMemo(int n){
        if (n < 2) return n;
        int a = 0, b = 1, c = 0;
        //其实我们每次把前两个值存下来就可以了。
        for (int i = 1; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
