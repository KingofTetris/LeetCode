package 每日一题;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2023/2/23 16:19
 */
public class 循环码排列_2023_02_23 {

    /**
     * 从0开始到(2^n) - 1，给这些数排序
     * 要求p[0] = start
     * p[i] p[i+1] 的二进制串只能有一位不相同 这种编码叫做格雷码
     * 另外p[0] 和 p[2^n-1]的二进制同样也只能有一位不同。 格雷码再加上这个条件就是循环码或者叫反射码
     * @param n
     * @param start
     * @return
     */
    public List<Integer> circularPermutation(int n, int start) {

        List<Integer> res = new LinkedList<>();

        //格雷码公式
        //第i个格雷码的公式为 x = (i/2) ^ i
        //而本题要求用start开始，则每一位都和start相异或就行了
        for (int i = 0; i < 1 << n; i++) { //左移乘2，右移/2。想象一下保持小数点不动，数字移动就知道大小变化了
            //实际上 1<<n 就是 Math.pow(2,n)
            res.add((i >> 1) ^ i ^ start);
        }
        return res;
    }
}
