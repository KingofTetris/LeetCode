package 十大排序;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2022/9/6
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    /**
     * 快速排序是原地排序，不需要辅助数组，但是递归调用需要辅助栈。
     *
     * 快速排序最好的情况下是每次都正好将数组对半分，这样递归调用次数才是最少的。复杂度为 O(NlogN)。
     *
     * 最坏的情况下，第一次从最小的元素切分，第二次从第二小的元素切分，
     * 如此这般。因此最坏的情况下也就是已经有序的情况下需要比较 N^2/2。
     *
     * 为了防止数组最开始就是有序的，在进行快速排序时需要随机打乱数组。shuffle
     * @param nums
     */
    @Override
    public void sort(T[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    private void shuffle(T[] nums){
        List<Comparable> list = Arrays.asList(nums);//把数组转化成List
        Collections.shuffle(list);//然后直接用工具类shuffle
        list.toArray(nums);//最后再把list洗牌后的内容给nums
    }

    private void sort(T[] nums, int l, int h) {
        if (h <= l)
            return;
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }

    private int partition(T[] nums, int l, int h) {
        int i = l, j = h + 1;
        T v = nums[l];
        while (true) {
            while (less(nums[++i], v) && i != h) ;
            while (less(v, nums[--j]) && j != l) ;
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }
}
