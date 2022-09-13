package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author KingofTetris
 * @Date 2022/9/9 14:28
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * 示例 1：
 *
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 *
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *  
 *
 * 限制：
 *
 * 2 <= nums.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer56_数组中数字出现的次数 {

    @Test
    public void test(){

    }

    /**
     * 简单粗暴的HashMap法，遍历一遍然后记录出现次数。
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0) + 1);
        }

        int[] res = new int[2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1){
                res[i] = entry.getKey();
                i++;
            }
        }
        return res;
    }


    /**
     * 还记得从一堆筷子里面找到那根短筷子的方法吗？(从一堆相同的数字中找到一个不同的数字)--->异或
     * 现在是从一堆成对的筷子中，找到两根不一样长的筷子。
     * @param nums
     * @return
     */
    public int[] singleNumbers2(int[] nums) {
        /**
         * 因为相同的数字异或为0，任何数字与0异或结果是其本身。
        所以遍历异或整个数组最后得到的结果就是两个只出现一次的数字异或的结果：即 z = x ^ y
         */
        int z = 0;
        for(int i : nums) z ^= i;
        /**
         *由x,y一定不相等，那么我们根据异或的性质可以知道：z不为0，那么z中至少有一位是1。
        我们通过一个辅助变量m来保存z中哪一位为1.（可能有多个位都为1，我们找到最低位的1即可）。
        举个例子：z = 10 ^ 2 = 1010 ^ 0010 = 1000,第四位为1.
        我们将m初始化为1，如果（z & m）的结果等于0说明z的最低为是0
        我们每次将m左移一位然后跟z做与操作，直到结果不为0.
        此时m应该等于1000，同z一样，第四位为1.
         */
        int m = 1;
        while((z & m) == 0) m <<= 1; //m每次左移1位，再和z相与，如果不为0那么这位就是1，继续重复这个过程
        //目的在于取到 z = x ^ y 最高位的 1，因为z中至少有一位是1。
        //这样拿着这个 m 去和所有的数字相与那么一定会把x,y分开成两组。一个组等于0 一组不等于0
        //然后问题就是变成了从一堆成对筷子里找出一根不成对的筷子。直接异或就行了。
        /**
         * 我们遍历数组，将每个数跟m进行与操作，结果为0的作为一组，结果不为0的作为一组
        例如对于数组：[1,2,10,4,1,4,3,3]，我们把每个数字跟二进制的(1000)做与操作，可以分为下面两组：
        nums1存放结果为0的: [1, 2, 4, 1, 4, 3, 3]
        nums2存放结果不为0的: [10] (碰巧nums2中只有一个10，如果原数组中的数字再大一些就不会这样了)
        此时我们发现问题已经退化为数组中有一个数字只出现了一次
        分别对nums1和nums2遍历异或就能得到我们预期的 x 和 y
        */
        int x = 0, y = 0;
        for(int i : nums) {
            //这里我们是通过if...else将nums分为了两组，一边遍历一遍异或。
            //跟我们创建俩数组nums1和nums2原理是一样的。
            if((i & m) == 0) x ^= i;
            else y ^= i;
        }
        return new int[]{x, y};
    }

    /**不看题解写一遍
     * @param nums
     * @return
     */
    public int[] singleNumbers3(int[] nums) {
        int x = 0,y = 0;//两个不同的数
        int z = 0;//0和任何数a字异或还是它本身a，所以选择0作为初始数字
        for (int num : nums) {
            z = z ^ num;
        }

        int m = 1;//从000...1开始找 z 的高位1 ，从而使得x,y一定能分在两个组里面
        //比如0001 0010 0100 1000...直到找到那个能区分x,y的m
        while ((z & m) == 0 ) m = m << 1; //从右往左去找z第一个不为0的位置，用来区分x,y

        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & m) == 0) x = x ^ nums[i]; //相与为0的为一组，不为0的为一组
            else y = y ^ nums[i];
        }

        return new int[]{x,y};
    }

}
