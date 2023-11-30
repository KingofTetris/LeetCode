package LeetCode数据结构与算法基础.二分查找专题;

import org.junit.Test;


/*注意二分查找是要求数组有序的*/
/*给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索nums中的 target，
如果目标值存在返回下标，否则返回 -1。


        示例 1:

        输入: nums = [-1,0,3,5,9,12], target = 9
        输出: 4
        解释: 9 出现在 nums 中并且下标为 4
        示例  2:
        输入: nums = [-1,0,3,5,9,12], target = 2
        输出: -1
        解释: 2 不存在 nums 中因此返回 -1


        提示：

        你可以假设 nums中的所有元素是不重复的。
        n将在[1, 10000]之间。
        nums的每个元素都将在[-9999, 9999]之间。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binary-search
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class 二分查找 {

    @Test
    public void test(){
        int[] nums = {-1,2,4,5,9,12};
        int k1 = 11;
        int k2 = 12;
        System.out.println(BinarySearch(nums, k1));
        System.out.println(BinarySearch(nums, k2));
    }


    public int BinarySearch(int[] nums,int key) {
        int high = nums.length - 1;
        int low = 0;

        while(low <= high){
            int mid = (low + high) >> 1; //右移一位比/2效率要高
            if(nums[mid] == key) return mid;
            if (nums[mid] < key) low = mid + 1; //mid右移一位
            if(nums[mid] > key)  high = mid -1;//mid左移一位
        }
        return -1;
    }
}
