package LeetCode数据结构基础.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2023/8/28
 */

//给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
// 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。

//这题就变成了有两个数字只出现1次。
//如果我们按照刚刚的通用思路去做。那么是没有办法的，因为这两个数数位相加可能刚好就是2，你再%2就变回0了。

//需要另辟蹊径
public class 只出现一次的数字III {

    public static void main(String[] args) {
        int[] nums = {1,2,1,3,2,5,3,5,8,9,9,100};
        int[] ints = singleNumberIII(nums);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] singleNumberIII(int[] nums) {
        //我们先对所有数字异或，可以得到 a ^ b 而且这个值一定不为0，因为a != b
        //然后呢?
        // 然后我们用 x & -x 可以取出x二进制中最低位的1
//        x & -x 这个神奇代码就是本题的关键 比如 6 (0110) 那么 右边第一个1 就是右边第二位，代表2
        //因为a != b 这个 x 又是异或以后的结果，那么这个1 不是来自a 就是来自 b
        //那么我们可以把原来的数组分为2个部分
        //1个是这个位置上是1的，1个就是这个位置不是1的。
        //那么a和b必然不同组。
        //然后我们再对这个组进行异或就可以求出a,b了。
        int x = 0;//注意初始值是0 0 ^ a = a;//0去异或任何数都是他本身
        for (int num : nums) {
            x = x ^ num;
        }
        int diff = x & -x;//得到最低位的1
        int AorB = 0;
        for (int num : nums) {
            if ((num & diff) != 0){ //如果num在这一位上是1,要特别小心这个条件！！
                //是!=0 不是==1！！！
                //比如    7（二进制表示为 111） 3（二进制表示为 011）
                // 7 & 3 == 3 (!=0也!=1，所以你写成==1的话就出问题了。)
                AorB ^= num;//求出这个为1的a或者b
            }
        }
        //现在有了AorB，我们的任务就是求出另外一个数。
        //这个就非常简单了。x = a ^ b,那么我们让 x ^ (AorB) 就是另外一个数
        return new int[]{AorB,x ^ AorB};
    }
}
