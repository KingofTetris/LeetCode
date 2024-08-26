package 校招笔试真题.联想._24春招后端工程师;


/**
 * 选择题问下面程序的运行结果
 */
public class BitCount {


    /**
     * 计算该整数的二进制表示中 1 的个数，并返回结果。
     * 这个程序实际上是在实现一个经典的算法，也被称为 Brian Kernighan 算法，
     * 用于计算一个无符号整数的二进制表示中包含的 1 的个数。这个算法的原理如下：
     *
     * 首先，通过一系列位操作将输入的整数 n 中连续的一位 1 压缩成一个 1，其余位置为 0。
     * 重复这个过程，直到整个整数 n 中的所有 1 都被计算出来。
     * 最终，返回计算出的 1 的个数。
     * 这个算法的思路是通过不断地消除整数中最低位的 1 来计算整数中包含的 1 的个数，这样可以减少计算的次数，提高效率。
     * @param n
     * @return
     */
    public static int function(int n) {
        n = (n & 0x55555555) + ((n >> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >> 16) & 0x0000ffff);
        return n;
    }

    public static void main(String[] args) {
        System.out.println(function(197));
    }
}
