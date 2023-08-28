package LeetCode_HOT100题;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/10/13 15:34
 */
public class 搜索旋转排序数组 {

    @Test
    public void test(){
        int[] nums = {4,5,6,7,0,1,2};
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
     * nums已经从小到大排好序了，但是在传递target时，
     * nums发生了未知的旋转。
     * 现在要你找出target的下标
     * 要求复杂度在O(logN) //想都不用想  一定是二分
     * @param nums
     * @param target
     * @return
     */


    // 因为是有序数组发生了左移
    // 那么一定存在一个分界线把数组分成有序的两段。
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0 ) return -1;
        if (n == 1) return target == nums[0] ? 0 : -1;


        //先看作是原来的有序数组
        int low = 0, high = n - 1;

        //还是经典的二分。
        while (low <= high){
            int mid = (low + high) / 2;
            if (nums[mid] == target){
                return mid;
            }

            //如果nums[0] 小于 nums[mid]
            //那么 [0,mid]是一组 [mid + 1,n-1]是另外一组
            if (nums[0] <= nums[mid]){ //等号的原因在于mid可以等于0
                //继续判断target是不是在[low,mid]之间
                if (nums[0] <= target && target < nums[mid]){
                    //如果在。我们在这个范围里面找
                    high = mid - 1;
                }
                else{ //如果不在，我们去另外一组里面找。
                    low = mid + 1;
                }
            }

            //另外一组
            else{
                //继续判断target在不在这个范围里面
                if (nums[mid] < target && target <= nums[n - 1]){
                    //如果在，那么
                    low = mid + 1;
                }
                else { //不在,去另外一组里面找
                    high = mid -1;
                }
            }
        }

        //如果low > high，那么说明target根本不在数组中。
        return -1;
    }
}
