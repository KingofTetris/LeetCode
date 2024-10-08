package LeetCode数据结构与算法基础.数学;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/9/6
 */
public class 点石成金_矩形分割成最少的正方形 {

    /**
     * 每次可以让一个最大的正方形区域被标记
     *
     * @param n
     * @param m
     * @return
     */

    //其实就辗转取余而已，问什么gpt，那玩意儿根本弱智。
    //就这么简单。
    public int fun(int n, int m) {
        if (n == m)
            return 1;
        if (Math.max(n, m) % Math.min(n, m) == 0)
            return Math.max(n, m) / Math.min(n, m);
        else
            return 1 + fun(Math.max(n, m) - Math.min(n, m), Math.min(n, m));
    }

    @Test
    public void test(){
        int n = 5, m = 3;
        int res = fun(n, m);
        System.out.println(res);
    }

}
