package 剑指offer第二版.位运算;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/16 16:52
 *
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 *  
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *  
 *
 * 提示：
 *
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer65_不用加减乘除做加法 {

    /**
     * 无进位和与异或(⊕)相同
     *
     * 进位结果和与(&)运算相同
     * @param a
     * @param b
     * @return
     */
    @Test
    public void test(){
        int a = -30;
        int b = 50;
        int add = add(a, b);
        System.out.println(add);
    }
    //那么s = a + b 就等于 无进位和 n  +  进位 c = n + c
    //然后重复这个相加的过程直到无进位。就可以结束了
    public int add(int a, int b) {
        while (b != 0){

            /**
             * >> 带符号右移
             * >>> 不带符号右移
             * 上面是右移，左移也一样
             *
             * 20的补码
             *     11111111111111111111111111101100
             * 20 >>2（右移） 带符号右移，符号位不动，其他位右移，缺的位置补符号位
             *     11111111111111111111111111111011
             * 20>>>2（无符号右移） 不带符号右移，符号位一起动，缺的位置补0
             *     00111111111111111111111111111011
             *
             */
            System.out.println("a=" + Integer.toBinaryString(a));
            System.out.println("b=" + Integer.toBinaryString(b));
            int aAndb = a & b;
            System.out.println("a与b为" + Integer.toBinaryString(aAndb));
            int c = aAndb << 1; //与完记得左移一位，因为进位在下一位。不是当前位。
            System.out.println("a与b << 1即c为" + Integer.toBinaryString(c));
            a ^= b; // a继承非进位。a ^ b
            System.out.println("a^=b为" + Integer.toBinaryString(a));
            b = c; // b继承进位  (a&b) << 1
            System.out.println("b=c后b为" + Integer.toBinaryString(a));
        }

        return a;
    }
}
