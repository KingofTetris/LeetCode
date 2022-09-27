package 每日一题;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/26 10:33
 */
public class 面试题17_19_消失的两个数字 {

    @Test
    public void test(){
//        System.out.println(2 & 2);
        int[] ints = missingTwo(new int[]{2});
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    /**
     *
     * O(n) O(1)
     * 数组nums中最大的数字为 nums.length + 2;
     *
     * 思路就是补全成从一堆成对的数字里面找到两个单一的数字
     * 也就是在nums后面补上 1-n n个数字
     * 使得原本存在的成对出现，不存在的单个出现
     * @param nums
     * @return
     */
    public int[] missingTwo(int[] nums) {
        int xorsum = 0; //0和任何数异或都是他本身，任何数自己和自己异或是0
        int n = nums.length + 2;

        for (int i = 0; i < nums.length; i++) {
            xorsum ^= nums[i];
        }

        for (int i = 1; i <= n; i++) {
            xorsum ^= i;
        }

        //现在xorsum 就是消失的两个数 x1,x2的异或结果
        //又因为x1 != x2 那么xorsum一定不为0.也就是这个异或结果一定存在一位为1，我们找最低位的1就行，假设他是第l位。
        //我们只需要找出这个1 我们拿着这个1就能把 1 - n 这堆数字分成两类
        //1类是第l位二进制为0的，2类是不为0的。
        //注意是为0和不为0 不是为1和不为1。因为1的位置未必是最低位。反应在数值上可能是1,2,4,8.......而不是1
        //刚好又把x1,x2分在1类和2类。
        //问题就变成了再一堆相同的数字里找一个不同的数字。
        //那么全部异或就是不同的数字了


        //一些常用的位运算
        // x & ( x - 1) while(x != 0) 用来计算有二进制有多少个1
        // x & (-x) 取得二进制的最低位1
        // (x + 1) >> 1 取二进制的最高位1

        //防溢出
//        int diff =  (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));


        //注意这些位运算 一般判断条件都是 和0比较，不是和1比较
        //判断奇偶才和1。
        int diff = 1;
        while ((xorsum & diff) == 0){ //左移直到找到不为0的
            diff = diff << 1;
        }
        
        //找到这个区分数以后
        //把为0的为1堆，不为0的为一堆就OK了


        int x1 = 0,x2 = 0;//异或从0开始
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & diff) != 0) x1 ^= nums[i];
            else x2 ^= nums[i];
        }

        for (int i = 1; i <= n ; i++) {
            if ( (i & diff) != 0) x1 ^= i;
            else x2 ^= i;
        }

        return new int[]{x1,x2}; //最后x1,x2就是消失的数字
    }
}
