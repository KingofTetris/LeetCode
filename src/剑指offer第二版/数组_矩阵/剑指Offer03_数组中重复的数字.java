package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Author KingofTetris
 * @Date 2022/7/6 14:49
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer03_数组中重复的数字 {

    @Test
    public void test(){
        int[] nums = {2,3,3,2,3,5,5,6,7,7,5,5,8,8,11,33,22};
        List<Integer> repeatNumber = findRepeatNumberArray(nums);
        System.out.println(repeatNumber);
    }
    /**
     * 最简单就直接暴力发一个一个找咯。O(n^2)
     * 搞错了，这个是找出所有重复元素
     */
    public List<Integer> findRepeatNumberArray(int[] nums) {
        int[] flag = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1;j < nums.length;j++){
                if (nums[i] == nums[j]){ //{2,3,3,2,3,5,5,6,7,7,5,5,8,8,11,33,22};
                    flag[i] = 1;
                    break; //有一次重复就跳出本次循环，
                }
            }
        }
        LinkedList<Integer>  list = new  LinkedList<> ();
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == 1 && !list.contains(nums[i]) ){ //添加，去重。
                    list.add(nums[i]);
            }
        }
        return list;
    }

    /**
     * O(n^2) 暴力法，慢的想死。
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1;j < nums.length;j++){
                if (nums[i] == nums[j]){ //{2,3,3,2,3,5,5,6,7,7,5,5,8,8,11,33,22};
                    return nums[i]; //有一次重复就结束，
                }
            }
        }
        return -1; //用-1表示没有重复的数字。
    }

    /**
     * 用集合的性质 时间O(n) 另外还用了额外的O(n)空间。
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int flag = -1; //重复的数字。
        for (int num : nums) {
            if (!set.add(num)){
                //HashSet.add(E e)返回添加成功或失败（重复） 所有如果添加失败，那么flag就是重复的数字
              flag = num;
              break;
            }
        }
        return flag;
    }

    /**
     * 还有O(n)的方法 空间有是O(n) 就是那一个数组出来记录这个数出现多少次，如果有 >1的就返回这个数
     * @param nums
     * @return
     */
    public int findRepeatNumber3(int[] nums) {
        int[] count = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(++count[nums[i]] > 1){
                return nums[i];
            }
        }
        throw new RuntimeException("Not Found");
    }

    /**
     * 空间O(1)的来咯 但是这个方法会改变原数组 不利于额外空间
     * 因为题目还有一个条件元素为 0-N-1
     * 所以可以建立一个映射 第一次遇到一个数字i 就把他 交换 到 nums[i] 注意是交换不是覆盖
     * 让nums[i] = i
     * 如果再次遇到 i 那么nums[i]此时一定 = i 就找出来了。
     * @param nums
     * @return
     */
    public int findRepeatNumber4(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {  //本来就成立就不用换了。所以continue i++ 不进行下面的nums[nums[i]] == nums[i] 判断
                i++;
                continue; //跳出本次循环
            }
            if(nums[nums[i]] == nums[i]) return nums[i];
            /**
             * 碰到 i 就把 nums[i] 和 nums[nums[i]]交换一下 让nums[i] = i
             */
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }
}
