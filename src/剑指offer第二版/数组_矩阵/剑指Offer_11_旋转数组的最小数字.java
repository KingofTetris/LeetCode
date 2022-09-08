package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

import java.util.Comparator;

/**
 * @Author KingofTetris
 * @Date 2022/7/13 20:18
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *
 * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。  
 *
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：numbers = [3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * 输入：numbers = [2,2,2,0,1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * n == numbers.length
 * 1 <= n <= 5000
 * -5000 <= numbers[i] <= 5000
 * numbers 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer_11_旋转数组的最小数字 {

    @Test
    public void test(){
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)Math.floor(Math.random() * 101);
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
        System.out.println(minArray(array));
    }
    /**
     * 意思就是本来是一个升序排序的（有重复元素）数组，现在旋转（右移）了一次，但不知道右移几位，要你快速找出最小的元素
     * 而且限制了长度在1-5000
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        /**
         * 直接用Java提供的双基准三路快速排序是很慢的，因为这是个基本有序数组 会接近最慢的情况O(n^2)
         */
//        Arrays.sort(numbers);//默认是升序 要实现降序就要实现Comparator接口，而且数组要是包装类 普通类型不能用这个实现降序
//        return numbers[0];

        /**
         * 当然直接暴力法一个一个去比 找到第一次出现n[i] < n[i+1]的地方就是最小的数， 时间复杂度是O(n)
         */
        /**
         *  数组加上(有序和基本有序)要想起来用二分！改进为二分查找 O(logN)  链表虽然也是顺序存储，但是通过下标找数的过程是O(n)。根本没有提升。
         */
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;//把相加转成相减无非就是怕溢出，因为两个low+high可能直接就溢出了。
            //所以改成用low加上区间长度的一半。就是区间的中间。
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high--;
            }
        }
        return numbers[low];

    }


    /**
     * 实现Comparator接口的compare方法
     */
    class MyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return -(o1-o2); //这样就是降序了
        }
    }
}
