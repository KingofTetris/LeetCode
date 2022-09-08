package LeetCode数据结构入门.day1.数组;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author KingofTetris
 * @File 数组包含重复元素
 * @Time 2021/9/26  23:01
 */

//利用set的无序，无重复性，比较数组和set的长度就行了
    //当然还能在add的时候就开始判断，add相同元素会返回false
public class 数组包含重复元素 {

    @Test
    public void test(){
        int[] nums = {1,2,3,4,5,6,6};
        System.out.println(containsDuplicate(nums));
    }

    //法一，利用set无重复性
//    public boolean containsDuplicate(int[] nums) {
//        Set<Integer> set = new HashSet<>();
//        for (int x : nums) {
//            //set添加
//            set.add(x);
////            if (!set.add(x)) {
////                return true;
////            }
//        }
//        return nums.length - set.size() > 0 ? true:false;
//    }

    //法二：先排序然后判断相邻元素是否相等
    public boolean containsDuplicate(int[] nums){
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i+1])
                return true;
        }
        return false;
    }

    //法三：如果题目要求不能利用额外空间 可以用双指针
}
