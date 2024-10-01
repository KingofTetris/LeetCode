package LeetCode数据结构与算法基础.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2024/9/28
 */

import java.util.*;


//TODO 到底怎么做。。
/**
 * 给你一个列表S
 * 你可以进行P次x+y操作，Q次x-y操作。
 * 每次操作后把对应的结果插入到列表S中。
 * 注意当集合只剩一个元素时候，不能再进行操作。
 * 小明想知道进行若干次操作后，max(S) - min (S) 的最大值是多少?
 * <p>
 * 例如输入：
 * 3 0 0
 * 5 1
 * 表示n = 3,P = 0,Q =0
 * 数组为 5 1
 * 那么max(S) - min(S) = 4
 * <p>
 * 或者输入：
 * 8 2 1
 * -8, 6, 5, 5, -4, -3, 1, -1
 * 表示n = 3,P = 0,Q =0
 * 数组为 -8, 6, 5, 5, -4, -3, 1, -1
 * 那么经过2次x+y，一次x-y后。最大的max(S)为(6+5+5-(-8))为24，min(S)为-4，那么max(S) - min(S) = 28。
 */
public class 最大极差 {

    public static void main(String[] args) {
        int n = 3; // 集合 S 中初始元素的个数
        int P = 2; // 进行x+y操作的次数
        int Q = 1; // 进行x-y操作的次数
        int[] S = {5, 1, -4, -1, -3, 5, 6, -8}; // 初始集合 S 中的元素
        //本来思路是让max最大，使得整体最小
        //但现在是max(S) - min(S) 有两个参数
        //min(S)尽量小也可以使结果最大。这就麻烦了。
        //所以你应该比较绝对值，绝对值大的放前面，如果是正数就相加，如果是负数就相减。
        Integer[] SS = new Integer[S.length];
        for (int i = 0; i < S.length; i++) {
            SS[i] = S[i];
        }
        //按绝对值从大到小排序
        //Arrays.sort(基本类型数组)只能从小到大
        // 想要用比较器从大到小排序，得转成包装类型。
        Arrays.sort(SS, (o1, o2) -> Math.abs(o2) - Math.abs(o1));
        System.out.println(Arrays.toString(SS));
        int maxDifference = calculateMaxDifference(P, Q, SS);
        System.out.println("最大差值为: " + maxDifference);
    }

    public static int calculateMaxDifference(int P, int Q, Integer[] arr) {
        return 0;
    }
}
