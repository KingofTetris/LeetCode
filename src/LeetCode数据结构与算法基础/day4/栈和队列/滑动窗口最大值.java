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
//        int[] nums = {1,-1};
//        int[] ints = maxSlidingWindow(nums, 3);
        //        System.out.println(Arrays.toString(ints));
        int[] res = maxSlidingWindow2(nums, 3);
        System.out.println(Arrays.toString(res));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        int len = nums.length - k + 1;
        //存放结果元素的数组
        int[] res = new int[len];
        int num = 0;
        //自定义单减队列，队头只维护最大值。
        MyMonotoneDecreaseQueue myQueue = new MyMonotoneDecreaseQueue();
        //先将前k的元素放入队列
        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        //求第一次的max
        res[num++] = myQueue.peek();

        for (int i = k; i < nums.length; i++) {
            //滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
            //如果当前元素就是队头元素，说明窗口已经滑动出队头元素能够到达的出口了
            //需要弹出。
            myQueue.poll(nums[i - k]);
            //滑动窗口加入最后面的元素
            myQueue.add(nums[i]);
            //记录对应的最大值
            res[num++] = myQueue.peek();
        }
        return res;
    }

    /**
     * 单调队列套路
     * 入（元素进入队尾，同时维护队列单调性）
     * 出（元素离开队首）
     * 记录/维护答案（根据队首）
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        // n个元素的数组最多有n-k+1个长为k的滑动窗口。
        int[] ans = new int[n - k + 1];
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

//解法一
//自定义单调队列
class MyMonotoneDecreaseQueue {
    Deque<Integer> deque = new LinkedList<>();
    //弹出元素时，比较当前要弹出的数值是否等于队列出口的数值，如果相等则弹出
    //同时判断队列当前是否为空

    /**
     * 这个单调队列poll的逻辑不是deque.size == k
     * 因为我们在add的时候就会remove掉一些元素。
     * 这样会导致虽然队列的大小已经是k了。
     * 但其实队头元素可能还是在这个窗口中。
     * 我们要poll的时机应该是当前要入队的元素等于队头元素？
     * //如果当前元素就是队头元素，说明窗口已经滑动出队头元素能够到达的出口了
     * //需要弹出。
     *
     * @param val
     */
    void poll(int val) {
        if (!deque.isEmpty() && val == deque.peek()) {
            deque.poll();
        }
    }
    //添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
    //保证队列元素单调递减
    //比如此时队列元素为3,1,马上2要入队，比1大，所以1弹出，此时队列：3,2
    //单减队列，只要前面有数字比当前的小就把前面小的依次弹出。

    /**
     * 为什么可以把前面不符合条件的元素都弹出呢？
     * 因为这题比较特殊，滑动窗口每次往后弹出一个元素。
     * 那么前面的这些元素，本来就是要遗弃掉的。
     *
     * @param val
     */
    void add(int val) {
        //把前面比val小的元素都消除。
        while (!deque.isEmpty() && val > deque.getLast()) {
            deque.removeLast();
        }
        //把val添加进去
        deque.add(val);
    }

    //队列队顶元素始终为最大值
    int peek() {
        return deque.peek();
    }
}
