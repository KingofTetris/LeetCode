package LeetCode数据结构与算法基础.动态递归;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/11/30
 */


/**
 *给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * 一个栈(无穷大)的进栈序列为1，2，3，…，n，有多少个不同的出栈序列?
 * 在一个凸多边形中，通过若干条互不相交的对角线，把这个多边形划分成了若干个三角形，那么n边形最大能划分为多少个三角形？
 * 在圆上选择2n个点,将这些点成对连接起来使得所得到的n条线段不相交的方法数。
 * 给定N个节点，能构成多少种不同的二叉搜索树。
 * 给定n对括号，求括号正确配对的字符串数
 *
 * 上面这些问题全部都是 卡特兰数 在中国叫明安图数
 */
public class 不同的二叉搜索树 {

    @Test
    public void test(){
        System.out.println(numTrees(5));
    }


    //DP
    public int numTrees2(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                //这道题的难点就是推出这个递推公式。不太容易。
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

/*    作者：力扣官方题解
    链接：https://leetcode.cn/problems/unique-binary-search-trees/solutions/329807/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    /**
     * n个节点可以有多少个不同的二叉树，以及n个元素入栈，任意时刻出栈，有多少种出栈序列，
     * 其实都是卡特兰数的应用场景。
     * @param n
     * @return
     */

    //h(n)=C(2n,n)/(n+1) (n=0,1,2,...)
    public int numTrees(int n) {
        // 提示：我们在这里需要用 long 类型防止计算过程中的溢出
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2L * i + 1) / (i + 2);
        }
        return (int) C;
/*
        作者：力扣官方题解
        链接：https://leetcode.cn/problems/unique-binary-search-trees/solutions/329807/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
