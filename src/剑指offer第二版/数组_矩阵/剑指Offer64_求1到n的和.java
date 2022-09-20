package 剑指offer第二版.数组_矩阵;

/**
 * @Author KingofTetris
 * @Date 2022/9/16 16:26
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * 示例 1：
 *
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 *
 * 输入: n = 9
 * 输出: 45
 *  
 *
 * 限制：
 *
 * 1 <= n <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/qiu-12n-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer64_求1到n的和 {

    /**
     *
     * @param n
     * @return
     */
    public int sumNums(int n) {
        //JDK8的流新特性 但本质的底层还是用了乘除和循环
//        return IntStream.range(1,n+1).sum();

        //多用一个布尔变量来辅助条件运行,当n==1时 递归终止 就实现了1+...+n
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;

    }
}
