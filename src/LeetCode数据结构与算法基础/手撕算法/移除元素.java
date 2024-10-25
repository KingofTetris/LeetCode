package LeetCode数据结构与算法基础.手撕算法;

/**
 * @author by KingOfTetris
 * @date 2024/10/25
 */
public class 移除元素 {


    //原地删除nums中的val。并且相对顺序不能改变
    //还是双指针解法
    //如果right = val,right++ left不动。
    //如果right != val,把right赋值给left

    //3,2,2,3 ,删掉3,
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    /*    作者：力扣官方题解
        链接：https://leetcode.cn/problems/remove-element/solutions/730203/yi-chu-yuan-su-by-leetcode-solution-svxi/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
