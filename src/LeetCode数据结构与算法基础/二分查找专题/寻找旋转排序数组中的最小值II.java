package LeetCode数据结构与算法基础.二分查找专题;

/**
 * @author by KingOfTetris
 * @date 2024/11/5
 */

//和I相比，还是多了带重复的元素
//总之有了重复值，就无法直接通过mid在 O(logN)内 来区分两个有序区间
//就必须两边right-- left++去找
//和搜索旋转排序数组II是一样的
public class 寻找旋转排序数组中的最小值II {
    //还是k神的图看着舒服。
    public int findMin(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (nums[m] > nums[j]) i = m + 1;
            else if (nums[m] < nums[j]) j = m;
            else j--;
        }
        return nums[i];
    }

       /*  作者：Krahets
        链接：https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/solutions/9474/154-find-minimum-in-rotated-sorted-array-ii-by-jyd/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
