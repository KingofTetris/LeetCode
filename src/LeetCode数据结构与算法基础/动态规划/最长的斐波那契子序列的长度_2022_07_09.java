package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 *
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 *
 * （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 *
 *  
 *
 * 示例 1：
 *
 * 输入: arr = [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
 * 示例 2：
 *
 * 输入: arr = [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
 *  
 *
 * 提示：
 *
 * 3 <= arr.length <= 1000
 * 1 <= arr[i] < arr[i + 1] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最长的斐波那契子序列的长度_2022_07_09 {
    @Test
    public void test(){
        int[] arr = {1,2,3,4,5,6,7,8};
        System.out.println(lenLongestFibSubseq(arr));
    }

    /**
     * 找出一个数组中的最长子序列，这个子序列是一个斐波那契数列
     * @param arr
     * @return 返回这个数列的长度
     *
     *
     */
    //法1 DP 只求长度，不求序列。
    public int lenLongestFibSubseq1(int[] arr) {
        Map<Integer, Integer> indices = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            indices.put(arr[i], i);
        }
        int[][] dp = new int[n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                int k = indices.getOrDefault(arr[i] - arr[j], -1);
                if (k >= 0) {
                    dp[j][i] = Math.max(dp[k][j] + 1, 3);
                }
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }

  /*  作者：力扣官方题解
    链接：https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/solutions/1654336/zui-chang-de-fei-bo-na-qi-zi-xu-lie-de-c-8trz/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    /**
     * 法二：枚举，锁定前两个值，一个个去找后面的值是否在arr中存在 但是没用到递增的性质
     * @param arr
     * @return
     * 时空：O(n^3) O(n)
     */
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : arr) { //利用map可以判断某个数在不在容器里的特点想到用map
            map.put(i,i);//value其实无所谓，把数组的值存为key才是重点，因为要用到HashMap.containsKey();
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1 ; j < arr.length; j++) {
                //{1,2,3,4,5,6,7,8};
                int pre = arr[i];//一前一后两个指针
                int cur = arr[j];
                int count = 2; //每个轮次都是固定两个数
                while (map.containsKey(pre+cur)){
                    count++;
                    int temp = pre + cur;
                    pre = cur;
                    cur = temp; //一匹配到两个都往后移动，pre = cur,cur = temp;
                }
                if (count >= 3){
                    ans = Math.max(count,ans);//每次有符合的就更新ans的长度。当然只保留最长的那个
                }
            }
        }
        return ans;
    }
}
