package 十大排序.十大排序通用模板;

/**
 * @author by KingOfTetris
 * @date 2022/9/6
 * 从数组中选择最小元素，将它与数组的第一个元素交换位置。再从数组剩下的元素中选择出最小的元素，
 * 将它与数组的第二个元素交换位置。不断进行这样的操作，直到将整个数组排序。
 *
 * 选择排序需要 N^2/2 次比较和 N 次交换，
 * 它的运行时间与输入无关，这个特点使得它对一个已经排序的数组也需要这么多的比较和交换操作。
 */
public class Selection<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        for (int i = 0; i < N - 1; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                /**
                 * 去找最小元素
                 */
                if (less(nums[j], nums[min])) {
                    min = j;
                }
            }
            /**
             * 找到后和i的元素互换
             */
            swap(nums, i, min);
        }
    } //选择排序

}
