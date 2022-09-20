package 十大排序;

/**
 * @author by KingOfTetris
 * @date 2022/9/6
 * 从左到右不断交换相邻逆序的元素，在一轮的循环之后，可以让未排序的最大元素上浮到右侧。
 * 在一轮循环中，如果没有发生交换，那么说明数组已经是有序的，此时可以直接退出。
 */
public class Bubble<T extends Comparable<T>> extends Sort<T> {

    /**
     * 下面是优化过的冒泡排序
     * 也就是实现了在一轮循环中，如果没有发生交换，那么说明数组已经是有序的，此时可以直接退出。
     * @param nums
     */
    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        boolean isSorted = false;//标记是否已经排好序
        for (int i = N - 1; i > 0 && !isSorted; i--) {
            isSorted = true;
            for (int j = 0; j < i; j++) {
                /**
                 * 每轮把大的放最右边
                 */
                if (less(nums[j + 1], nums[j])) {
                    isSorted = false;
                    swap(nums, j, j + 1);
                }
            }
        }
    }
}
