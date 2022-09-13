package 剑指offer第二版.数组_矩阵;

import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/9/13 16:53
 *
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组 不为空 的情况下，1 ≤ k ≤ nums.length。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer59_I_滑动窗口的最大值 {

    /**
     * 暴力枚举。O(kn) 超时
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];//滑动窗口的数量
        int start = 0,end = k - 1;//end写成k-1的话。下面的窗口遍历就要写成 <=
        int count  = 0;
        for (; end < nums.length; ) {
            //计算每个滑动窗口的最大值
            int max = Integer.MIN_VALUE; //每个窗口都把max先标为min
            for (int i = start; i <= end; i++) {
                if (nums[i] > max){
                    max = nums[i];
                }
            }
            //记录下最大值
            res[count] = max;
            count++;

            //推动滑动窗口
            start++;
            end++;
        }

        return res;
    }

    /***
     * 大根堆每次取最大值，O(Nlogk) 比上面稍微好一点
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) { return new int[]{};}


    /**
     * 维护单调递减队列每次输出队列最左端的值(最大值)
     *
     * 队列里面存储的是元素的下标，这个队列里面下标对应的元素是递减的
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {

        if (nums.length == 0 || k > nums.length || k == 0)
            return new int[]{};
        //LinkedList 可以当链表，普通队列，双端队列
        LinkedList<Integer> queue = new LinkedList<>();

        int resIndex = 0;
        int res[] = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            //1.清理超窗元素
            if ( !queue.isEmpty() && queue.peek() == i - k){ //也就是当前元素下标减去k 刚好是上一个窗口的开头下标
                queue.remove();//去掉头
            }

            //2.维护单调递减队列
            //去掉所有比nums[i] 入队元素更小的旧元素 保持first一定是最大值
            while (!queue.isEmpty() && nums[i] >= nums[queue.getLast()] ){ //如果下一个元素更大，那就把最后的元素删除
                queue.removeLast();
            }

            //3.新元素下标入队
            queue.addLast(i);

            //4.获取最大值(当 i >= k - 1时)
            if (i >= k - 1)  res[resIndex++] = nums[queue.getFirst()];

        }
        return res;
    }
}
