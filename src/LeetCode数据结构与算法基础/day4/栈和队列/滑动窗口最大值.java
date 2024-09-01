package LeetCode数据结构与算法基础.day4.栈和队列;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/13
 */
public class 滑动窗口最大值 {

    //要求时间复杂度为O(n)
    //这就需要借助一个神奇的单调队列
    //单调队列和单调栈其实是差不多的。
    //都是队列和栈中的元素保持一个单增或者单减。
    //这题要我们求最大值
    //那么我们就来单减。
    //这样队头才是维护的max
    @Test
    public void test() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = maxSlidingWindow2(nums, 3);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 单调队列套路
     * 入（元素进入队尾，同时维护队列单调性）
     * 出（元素离开队首）
     * 记录/维护答案（根据队首）
     *
     * 及时去掉无用数据，保证队列一定有序。 就是单调队列的核心，单调栈其实也一样
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        // n个元素的数组最多有n-k+1个长为k的滑动窗口。
        int[] ans = new int[n - k + 1];

        //维护一个从队尾到队头的单增队列
        Deque<Integer> q = new ArrayDeque<>(); // 双端队列

        //q维护的是数组的下标，比维护值要容易理解一些。
        for (int i = 0; i < n; i++) {
            // 1. 入，队列非空，队尾元素小于nums[i]，及时丢弃。
            // 注意这里是while，只要last元素一直比nums[i] 就一只丢弃。
            while (!q.isEmpty() && nums[q.getLast()] <= nums[i]) {
                q.removeLast(); // 维护 q 的单调性
            }
            q.addLast(i); // 把元素的下标入队，而不是值
            // 2. 出，如果 i - 队头元素的下标 >= k 说明队头已经从滑动窗口出去了，舍弃队头
            if (i - q.getFirst() >= k) { // 队首已经离开窗口了
                q.removeFirst();
            }
            // 3. 记录答案，当i >= k - 1的时候说明已经形成了长为k的窗口，就需要记录一次答案。
            if (i >= k - 1) {
                // 由于队首到队尾单调递减，所以窗口最大值就是队首
                ans[i - k + 1] = nums[q.getFirst()];
            }
        }
        return ans;

      /*  作者：灵茶山艾府
        链接：https://leetcode.cn/problems/sliding-window-maximum/solutions/2499715/shi-pin-yi-ge-shi-pin-miao-dong-dan-diao-ezj6/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
