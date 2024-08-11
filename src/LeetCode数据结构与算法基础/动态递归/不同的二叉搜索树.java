package LeetCode数据结构与算法基础.动态递归;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/11/30
 */


/**
 *
 */
public class 不同的二叉搜索树 {

    @Test
    public void test(){
        System.out.println(numTrees(5));
    }

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
