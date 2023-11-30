package LeetCode数据结构与算法基础.哈希表;

import java.util.HashMap;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,
 * 计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
 * <p>
 * 例如:
 * <p>
 * 输入:
 * <p>
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 输出:
 * <p>
 * 2
 * <p>
 * 解释:
 * <p>
 * 两个元组如下:
 * <p>
 * (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class 四数之和II {


    //    static List<List<Integer>> res = new LinkedList<>();
//    static List<Integer> path = new LinkedList<>();
    //不重复的四元组，其实有点像回溯啊。但是回溯看起来又有点麻烦。
    //因为这个是四个数组，并不是一个数组，除非你把他们四个拼成一颗n叉树。
    //太麻烦。正确的做法是哈希表
    //而且本题不要求相同的内容去重，只要下标不同就行了
    // 只是问你有多少种满足的方案数目，并不要求你求出具体的方案。
    public static void main(String[] args) {
        int[] a = {1, 2};
        int[] b = {-2, -1};
        int[] c = {-1, 2};
        int[] d = {0, 2};
        solution(a, b, c, d);

    }

    public static int solution(int[] a, int[] b, int[] c, int[] d) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        //四数同样还是先固定其中两个
        for (int i : a) {
            for (int j : b) {
                int sum = i + j;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        //剩余两个之和应该要为-sum
        for (int i : c) {
            for (int j : d) {
                int sum = i + j;
                //如果map去get(-sum) 有值的话，说明前面两个数出现过，那么就可以 +val
                res += map.getOrDefault(-sum,0);
            }
        }
        return res;
    }
}
