package 每日一题;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/7/21 17:07
 */


/**
 * Java的移位运算
 * >>（算术右移）：符号位不变，并用符号位补空缺的高位
 *
 * <<（算术左移）：符号位不变，低位补0
 *
 * >>>（无符号右移）：低位溢出，高位补 0
 *
 * 右移相当于/2  左移相当于*2
 * tips:Java没有无符号左移
 * 这是因为Java没有无符号数(unsigned)。java中有”无符号右移“，没有无符号左移
 * 如果进行无符号左移的话，Java的符号位就被丢掉了，相当于没有符号位了。没有符号位不就变成无符号数了吗？！
 */
public class 不用加号实现加法 {
    @Test
    public void test(){
        System.out.println(aplusb(13, 5));
    }

    /**
     * 首先你要明白的是这些int都占4个B 32bit。符号为在最前面的第32位。
     * @param a
     * @param b
     * @return
     */
    public int aplusb(int a,int b){

        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        /**
         * 分成无进位加法和进位两部分
         * 进位不可能一直持续，只要进位为0就可以停止了
         */
        int i = 1;
        while (b != 0){
            int _a = a^b; //求出无进位的部分
            int c = (a&b); //求出进位部分
            int _b = c << 1; //因为只有1+1会产生进位，1+0，0+0，0+1均不会产生进位这时我们发现这刚好符合（a&b)<<1的性质
            //a&b = 1就往前进一位

            //最后
            //第三步：让((a&b)<<1 )^(a^b)就相当于无进位部分加上进位，然而这样又会产生进位，因此就重复循环。直到进位为0结束。
            System.out.println("第" + i + "轮时 a^b的二进制:" + Integer.toBinaryString(_a));
            System.out.println("第" + i + "轮时 (a&b)的二进制:" + Integer.toBinaryString(c));
            System.out.println("第" + i + "轮时 (a&b)<<1的二进制:" + Integer.toBinaryString(_b));
            a = _a;
            b = _b;
            i++;
        }

        return a;
    }
}
