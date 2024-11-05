package LeetCode数据结构与算法基础.二分查找专题;

import org.junit.Test;
/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）
 * 上进行了 旋转，使数组变为 [nums[k], nums[k+1], ...,nums[n-1], nums[0], nums[1], ..., nums[k-1]]
 * （下标 从 0 开始 计数）。
 * <p>
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，
 * 则返回它的下标，否则返回 -1 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 */

/**
 * @Author KingofTetris
 * @Date 2022/10/13 15:34
 */
public class 搜索旋转排序数组 {

    //最简单就是遍历一遍数组去找target的下标，但是面试官肯定不可能让你这样做的。
    @Test
    public void test() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int search = search(nums, 0);
        System.out.println(search);
        /**
         * 因为这涉及到Java 运算符优先级的问题
         *
         * + 要优先于 >>
         * 4 + (6 - 4) >> 1所以
         * 其实是 6 >> 1 = 3
         * 分开当然就不存在优先级的问题了
         * 那么你要想要让>>在前，就加上括号
         *
         * 一般最常冲突的就是 + - * / % 和移位运算符
         * 要记住移位运算符 是小于 四则运算和 % 的。
         * 附送Java运算符优先级
         *
         * 优先级        运算符                                                          结合性
         *             1            ()、[]、.                                            从左向右
         *             2            !、~、++、--                                            从右向左
         *             3            *、/、%                                                从左向右
         *             4            +、-                                                    从左向右
         *             5            <<、>>、>>>                                            从左向右
         *             6            <、<=、>、>=、instanceof                                从左向右
         *             7            ==、!=                                                从左向右
         *             8            &                                                    从左向右
         *             9            ^                                                    从左向右
         *             10            |                                                    从左向右
         *             11            &&                                                    从左向右
         *             12            ||                                                    从左向右
         *             13            ?:                                                    从右向左
         *             14            =、+=、-=、*=、/=、%=、|=、^=、~=、<<=、>>=、>>>=            从右向左
         * ————————————————
         * 版权声明：本文为CSDN博主「小母鸡...」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
         * 原文链接：https://blog.csdn.net/weixin_58569983/article/details/125900188
         */

        ////为什么 low + (high - low) >> 1 不对？？
//        int a =  4 + (6 - 4) >> 1; //3???
//        int a =  (6 - 4) >> 1;
//        int b = 4;
//        System.out.println(a + b); //分开加就是5??
    }

    /**
     * 要求复杂度在O(logN) //想都不用想  一定是二分
     * @param nums
     * @param target
     * @return
     */

    // 因为是有序数组发生了左移
    // 那么一定存在一个分界线把数组分成有序的两段。

    /**
     * 那么整体的思路就是
     *
     * 1.将数组一分为二。（其中有一个一定是有序的；另一个则是有序或部分有序的）
     * 2.此时如果target在有序部分里，则用二分法查找。
     * 3.否则进入无序部分查找，返回1。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return target == nums[0] ? 0 : -1;
        //先看作是原来的有序数组
        int low = 0, high = n - 1;
        //还是经典的二分。
        while (low <= high) {
            int mid = (low + high) / 2;
            //如果nums[mid] == target
            //直接返回mid
            if (nums[mid] == target) {
                return mid;
            }
            //如果nums[0] 小于 nums[mid]
            //那么 [0,mid]是一组 [mid + 1,n-1]是另外一组
            //比如 4,5,6,7,0,1,2
            //4 <= 7 那么[0,mid],[mid+1,n-1]自然分成两组有序
            //对有序的继续二分即可。
            if (nums[0] <= nums[mid]) { //等号的原因在于mid可以等于0
                //判断target是不是在0到mid之间。
                if (nums[0] <= target && target < nums[mid]) {
                    //如果在。我们在这个范围里面找
                    high = mid - 1;
                } else { //如果不在，我们去另外一组里面找。
                    low = mid + 1;
                }
            }
            //如果nums[0] > nums[mid]
            //比如 6 7 8 9 1 2 3 4 5
            //6 > 1 那么[0,mid - 1],[mid,n-1]为一组
            else {
                //判断targe是不是在mid到n-1之间
                if (nums[mid] < target && target <= nums[n - 1]) {
                    //如果在，那么
                    low = mid + 1;
                } else { //不在,去另外一组里面找
                    high = mid - 1;
                }
            }
        }
        //如果low > high，那么说明target根本不在数组中。
        return -1;
    }
}
