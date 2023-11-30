package LeetCode数据结构与算法基础.day4.栈和队列;

/**
 * @author by KingOfTetris
 * @date 2023/9/13
 */

import java.util.*;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 提示：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 $O(n \log n)$ , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案
 */
public class 前K个高频元素 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int[] topKFrequent = topKFrequent(nums, 2);
        for (int i : topKFrequent) {
            System.out.println(i);
        }
    }

    //复杂度至少要O(nlogn),其实就是堆
    public static int[] topKFrequent(int[] nums, int k) {
        // 优先级队列，为了避免复杂 api 操作，pq 存储数组
        // 按o[1]从大到小排序。
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> -o[1]));
//        PriorityQueue<int[][]> pq2 = new PriorityQueue<>(Comparator.comparingInt(o -> o[0][1]));
        //二维数组排序不建议用上面的，改用Arrays
//        int[][] intervals = new int[2][2];
        //按照每个一维数组的o[1]进行从小到大排序。
//        Arrays.sort(intervals,Comparator.comparingInt(o->o[1]));
        int[] res = new int[k]; // 答案数组为 k 个元素
        Map<Integer, Integer> map = new HashMap<>(); // 记录元素出现次数
        for(int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        //JDK8是不能用var的，老实用Entry
        for(Map.Entry<Integer,Integer> x : map.entrySet()) { // entrySet 获取 k-v Set 集合
            // 将 kv 转化成数组
            int[] tmp = new int[2];
            tmp[0] = x.getKey();
            tmp[1] = x.getValue();
            pq.offer(tmp);
//            if(pq.size() > k) {
//                pq.poll();
//            }
        }
        for(int i = 0; i < k; i ++) {
            res[i] = pq.poll()[0]; // 获取优先队列里的元素
        }
        return res;
    }
}
