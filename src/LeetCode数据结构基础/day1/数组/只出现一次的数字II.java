package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

//在一个数组中，除了某个数，其他数字都出现3次，请你找出这个数
public class 只出现一次的数字II {

    @Test
    public void test(){
        int[] a = {4,1,2,1,2,1,2};
        System.out.println(singleNumberII(a));
    }

    private int singleNumberII(int[] nums) {
        //介绍一个通用的方法，遍历统计
        int[] counts = new int[32];//32的原因是int类型占32个字节。
        //那么我们用这个32长度的int数组就可以统计所有字符出现的次数。
        //对于那些3次的，这些下标上要么是0，要么是3.
        //然后我们再考虑独特的那个数。
        //这个数的出现会导致数组出现新的数字，1和4
        //这个时候我们只需要拿着现在的[1,4]数组减去原来的[0,3]数组
        //就得到了独特的这个数
        //或者说我们拿着每个数字对3取余就获得了独特的数。

        /**
         * 所以到这里这个通用方法就出现了，比如求一个数组中某个数字只出现了1次，
         * 其他数字都出现了m次，那么你只需要把3改成m就可以了。
         *
         */
        for (int num : nums) {
            for (int i = 31; i > 0; i--) {
                counts[i] += num & 1;//每次取最低位
                num = num >>> 1;//然后无符号右移，高位补0
            }
        }
        //最后我们对3取余
        for (int i = 0; i < counts.length; i++) {
            counts[i] = counts[i] % 3; //这样我们就获得了独特数字的 32位
        }
        //最后我们从2进制转为10进制即可
        int res = 0;
        int base = 2;
        int pow =  0;
        for (int i = 31; i > 0 ; i--) {
            res += counts[i] * Math.pow(base,pow);
            pow++;//每次pow要加1
        }
        return res;
    }


}
