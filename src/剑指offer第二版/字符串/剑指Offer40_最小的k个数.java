package 剑指offer第二版.字符串;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author KingofTetris
 * @Date 2022/8/30 15:33
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer40_最小的k个数 {


    @Test
    public void test(){
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = (int) (Math.random() * 101);//让num是 0 - 100之间的随机数 要注意(Math.random() * 101)这个括号不能少，不然全是0
            System.out.print(nums[i] + "\t");
        }
        System.out.println();
        quickSort(nums,0,nums.length - 1);
        for (int i = 0; i < 10; i++) {
            System.out.print(nums[i] + "\t");
        }

    }
    /**
     * Top-K问题
     * JDK自带双端排序
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOf(arr,k);
    }

    /**
     * 堆排序
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        int[] vec = new int[k];
        if (k == 0) { // 排除 0 的情况
            return vec;
        }
        //大根堆 大根堆堆排序是从小到大，小根堆是从大到小。
        //因为排序后的结果还是存放在堆中(没错，PriorityQueue就是堆)。先排好的数字会放在末尾。
        //而且默认是小根堆。所以要修改为大根堆。
        //大根堆排序是正序的，小根堆逆序。
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return -(num1 - num2);//加上负号就是从大到小排序，也就是大根堆
            }
        });
        for (int i = 0; i < k; ++i) {
            queue.offer(arr[i]); //首先将前 k 个数插入大根堆中
        }
        for (int i = k; i < arr.length; ++i) { //然后拿这K个元素和后面的元素对比
            /**
             * 随后从第 k+1个数开始遍历，
             * 如果当前遍历到的数比大根堆的堆顶的数要小，
             * 就把堆顶的数弹出，再插入当前遍历到的数。
             * 最后将大根堆里的数存入数组返回即可。
             */
            if (queue.peek() > arr[i]) { //queue.peek是堆的堆顶，每次把大的弹出，留下前K个小的。
                queue.poll();
                queue.offer(arr[i]);//JDK8为上滤操作。
            }
        }
        for (int i = 0; i < k; ++i) {
            vec[i] = queue.poll();
        }
        return vec;
    }
    /**
     * 手写快速排序
     *
     * @param arr
     * @param start 需要排序的区间start-end
     * @param end
     */
    private void quickSort(int[] arr, int start, int end) {
        // 子数组长度为 1 时终止递归
        if (start >= end) return;
        // 两个哨兵i,j和一个基准arr[start]
        int i = start, j = end;

        while (i < j) {
            while (i < j && arr[j] >= arr[start]) j--; //先从右往左找比基准小的
            while (i < j && arr[i] <= arr[start]) i++; //然后从左往右找比基准大的
            /**
             * 找到以后交换两个位置的数,使得比基准大的在基准右边，小的在基准左边。
             */
            swap(arr, i, j);
        }
        /**
         * i == j时，就是基准的最终位置。
         */
        swap(arr, i, start);
        // 递归左（右）子数组执行哨兵划分
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }

    /**
     * Java数组两个数的交换可以写成函数
     * 深入了解的话，这是因为加上了arr，存储在Heap而不是Stack中，
     * 就不会出现方法栈帧结束，临时变量（形参和局部变量）就被销毁。从而实现交换。
     * @param arr
     * @param i
     * @param j
     */
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
