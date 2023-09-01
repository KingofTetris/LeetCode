package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 只出现一次的数字
 * @Time 2021/10/10  11:45
 */

/*给定一个非空整数数组，除了某个元素只出现一次以外，
其余每个元素均出现两次。找出那个只出现了一次的元素。

        说明：

        你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

        示例 1:

        输入: [2,2,1]
        输出: 1
        示例 2:

        输入: [4,1,2,1,2]
        输出: 4*/


    //其实和算法课上的筷子问题一样，找到那根没人匹配的筷子。
    //拓展：求一个1-n数组中单独的两个数字。
public class 只出现一次的数字 {

    @Test
    public void test(){
        int[] a = {4,1,2,1,2};
        System.out.println(singleNumber(a));
    }

    //异或就是找茬，不同就是1，相同就是0
    //异或的特殊性质： 自己和自己异或为0，0和任何数异或都是他本身
    //异或运算又满足结合律，交换律。所以中间相同的数一异或全是0，最后只留下一个单独的数single
    public int singleNumber(int[] nums){
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }


}
