package 十大排序.十大排序Java手写版;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/22
 */
//TODO 堆排序代码看不懂，有时间研究一下。快速排序，归并排序和堆排序是面试手撕的重点题目
public class HeapSort {
    @Test
    public void test(){
        int[] nums = {1,9,8,4,2,5,7};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
    public void heapSort(int[] nums) {
        int n = nums.length;
        // 构建最大堆
        buildMaxHeap(nums, n);
        // 逐步将最大元素移动到堆末尾，并重新调整堆
        for (int i = n - 1; i > 0; i--) {
            // 交换堆顶元素（最大值）和堆末尾元素
            swap(nums, 0, i);
            // 调整堆，将剩余元素重新构建为最大堆
            heapify(nums, i, 0);
        }
    }

    // 构建最大堆
    private void buildMaxHeap(int[] nums, int n) {
        // 从最后一个非叶子节点开始，依次向上调整每个子树
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }
    }

    // 调整堆的过程，使其满足最大堆的性质
    private void heapify(int[] nums, int n, int i) {
        int largest = i; // 当前子树的根节点索引
        int left = 2 * i + 1; // 左子节点索引
        int right = 2 * i + 2; // 右子节点索引

        // 寻找当前节点、左子节点和右子节点中的最大值
        if (left < n && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < n && nums[right] > nums[largest]) {
            largest = right;
        }
        // 如果最大值不是当前节点，则交换最大值和当前节点，并继续调整被交换的子树
        if (largest != i) {
            swap(nums, i, largest);
            heapify(nums, n, largest);
        }
    }

    // 交换数组中两个元素的位置
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
