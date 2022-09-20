package 十大排序;

/**
 * @author by KingOfTetris
 * @date 2022/9/6
 */
public abstract class MergeSort<T extends Comparable<T>> extends Sort<T> {
    protected T[] aux; //辅助数组

    /**
     * 归并排序算法思想：分治+归并
     *
     * 1、把长度为n的输入序列分成两个长度为n/2的子序列；
     *
     * 2、对这两个子序列分别采用归并排序；
     *
     * 3、 将两个排序好的子序列合并成一个最终的排序序列。
     * @param nums
     * @param l
     * @param m
     * @param h
     */
    protected void merge(T[] nums, int l, int m, int h) {

        int i = l, j = m + 1;

        for (int k = l; k <= h; k++) {
            aux[k] = nums[k]; // 将数据复制到辅助数组
        }

        for (int k = l; k <= h; k++) {
            if (i > m) {
                nums[k] = aux[j++];

            } else if (j > h) {
                nums[k] = aux[i++];

            } else if (aux[i].compareTo(aux[j]) <= 0) {
                nums[k] = aux[i++]; // 先进行这一步，保证稳定性

            } else {
                nums[k] = aux[j++];
            }
        }

    }
}
