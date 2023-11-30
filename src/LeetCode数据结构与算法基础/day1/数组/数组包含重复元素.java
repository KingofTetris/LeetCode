package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        System.out.println(containsDuplicate1(nums));
    }

    //法一，利用set无重复性 需要O(n)的额外空间。
    public boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            //set添加失败就说明有重复元素
            if (!set.add(x))
                return true;
        }
        return false;
    }

    //法二：先排序然后判断相邻元素是否相等 O(nlogn)的时间复杂度
    public boolean containsDuplicate(int[] nums){
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i+1])
                return true;
        }
        return false;
    }

    //法三：如果题目要求不能利用额外空间 其实要判断一组数里面有没有重复元素，就相当于用一根筷子去匹配另外一根筷子
    //位运算 异或 a ^ a = 0
    //          a ^ b = 1
    //          a ^ b ^ a
    //  不对不对不对，这题不是找出只出现一次的数。。
    //  从一组筷子里面找出不匹配的单只筷子才是用异或。。
    // 因为 a ^ a = 0 b ^ b = 0
    // 那么 ( a ^ c ^ a ^ b ^ b) 因为异或是满足结合律的。
    // 那么到最后一定是 (0 ^ c) = c;//就找出来了。
    //而这题是要判断有没有重复元素，没法用这个方法。
}
