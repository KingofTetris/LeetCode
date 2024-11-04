package LeetCode数据结构与算法基础.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2024/11/3
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 * <p>
 * 一次煎饼翻转的执行过程如下：
 * <p>
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 * <p>
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。
 * 任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,4,1]
 * 输出：[4,2,4,3]
 * 解释：
 * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
 * 初始状态 arr = [3, 2, 4, 1]
 * 第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
 * 第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
 * 第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
 * 第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。
 * 示例 2：
 * <p>
 * 输入：[1,2,3]
 * 输出：[]
 * 解释：
 * 输入已经排序，因此不需要翻转任何内容。
 * 请注意，其他可能的答案，如 [3，3] ，也将被判断为正确。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= arr.length 注意这个条件!
 * arr 中的所有整数互不相同（即，arr 是从 1 到 arr.length 整数的一个排列）
 */
public class 煎饼排序 {


    @Test
    public void test() {
        int[] A = {3, 2, 1, 5, 4};
        System.out.println("原数组" + Arrays.toString(A));
        List<Integer> list = pancakeSort(A);
        System.out.println(list);
    }

    /**
     * 重要思路：我们把后面的数字先排好序，这样再翻转前面的时候就不会影响到后面
     * 有点类似于冒泡排序
     * 所以，先把最大的数字放到最后，然后再把次大的位置放在倒数第二个位置……
     * 如何把最大的数字放到最后呢？一个很简单的想法就是先把它翻转到第一个位置上去！
     * <p>
     * 所以，思路很清晰了：
     * 每次找到还没排序的数字之中最大的数字的位置，把这个位置之前的数字翻转，这一步使得最大数字去了最前面；
     * 第二步，再次翻转，把最大位置翻到它应该在的位置上去。
     *
     * @param A
     * @return
     */
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        int N = A.length;
        int pos, i;
        for (pos = N; pos > 0; pos--) {
            //因为1 <= arr[i] <= arr.length 注意这个条件!
            //而且arr 中的所有整数互不相同（即，arr 是从 1 到 arr.length 整数的一个排列）
            //这就限定了这个数组只能是连续的1到n的数组，只是无序而已。
            //N一定是最大元素。而且他一定存在。
            for (i = 0; A[i] != pos; i++) ;//找到最大元素对应的下标。
            reverse(A, 0, i);
            System.out.println("把0到" + i + "个数反转结果:" + Arrays.toString(A));
            if (i != 0)
                res.add(i + 1);
            reverse(A, 0, pos - 1);
            System.out.println("把0到" + (pos - 1) + "个数反转结果:" + Arrays.toString(A));
            res.add(pos);
        }
        return res;
    }

    private void reverse(int[] A, int start, int end) {
        while (start < end) {
            int tmp = A[start];
            A[start] = A[end];
            A[end] = tmp;
            start++;
            end--;
        }
    }

   /* 作者：负雪明烛
    链接：https://leetcode.cn/problems/pancake-sorting/solutions/1275857/fu-xue-ming-zhu-tu-jie-jian-bing-pai-xu-jto8z/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
