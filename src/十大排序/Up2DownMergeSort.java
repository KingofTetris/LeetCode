package 十大排序;

/**
 * @author by KingOfTetris
 * @date 2022/9/6
 */
public class Up2DownMergeSort<T extends Comparable<T>> extends MergeSort<T> {

    @Override
    public void sort(T[] nums) {
        aux = (T[]) new Comparable[nums.length];
        sort(nums, 0, nums.length - 1);//这里是一个重载
    }

    private void sort(T[] nums, int l, int h) {
        if (h <= l) { //分裂的条件当 h>l的时候结束，也就是分到剩下一个元素的时候
            return;
        }
        int mid = l + (h - l) / 2; //每次取出中间位置

        sort(nums, l, mid);  //把前半部分排序
        sort(nums, mid + 1, h); //把后半部分排序
        merge(nums, l, mid, h); //整体排序
    }
}
