package LeetCode数据结构与算法基础.双指针;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 有序数组的平方
 * @Time 2021/9/20  9:12
 */

/*给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，
要求也按 非递减顺序 排序。



        示例 1：

        输入：nums = [-4,-1,0,3,10]
        输出：[0,1,9,16,100]
        解释：平方后，数组变为 [16,1,0,9,100]
        排序后，数组变为 [0,1,9,16,100]
        示例 2：

        输入：nums = [-7,-3,2,3,11]
        输出：[4,9,9,49,121]


        提示：

        1 <= nums.length <= 104
        -104 <= nums[i] <= 104
        nums 已按 非递减顺序 排序


        进阶：

        请你设计时间复杂度为 O(n) 的算法解决本问题

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class 有序数组的平方 {
    @Test
    public void test(){
       int test1[] = {-8,-3,-2,0,2,4,8};
       int test2[] ={2,3,4,5};
       int test3[] ={-5,-3,-2,-1};

       int a[] = sortedSquares(test1);
        for (int i = 0; i < a.length ; i++) {
            System.out.print(a[i] + "\t");
        }

    }

//    方法1：简单的把每个数字平方放到新数组中排序即可
//    时间复杂度O(nlogn) 空间O(n)
   /* public int[] sortedSquares(int[] nums){
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[i] * nums[i];
        }
        Arrays.sort(temp);
        return temp;
    }*/

/**    法2：考虑单调不减，可以找到数组中负数和非负数之间的分界线 假设为negtive
    至此 nums[0] - nums[negtive] 都是负数，平方后递减
    nums[negtive+1] - nums[n - 1]都是非负数，平方后递增
    两个有序数组排序 考虑用归并排序
    public int[] sortedSquares(int[] nums){
        int negetive = -1;
        int n = nums.length;

        //普通查找分界线negetive 或者因为有序 所以可以二分查找，稍微快一点点。
        for (int i = 0; i < n; i++) {
            if(nums[i] < 0){
                negetive = i;
            }
            else
               break;
        }

        int[] ans = new int[n];
        int index = 0,i = negetive,j = negetive + 1;//分两段
        // i是分割点 负数结尾，j是非负开头



        //下面这段归并逻辑比较复杂
        //i >= 0则至少有一个非负数
        //j < n考虑边界 超过数组长度肯定是不行的
        while(i >= 0 || j < n ){
            if(i < 0){  //negetive 还是-1，数组中没有负数 直接平方就行
                ans[index] = nums[j] * nums[j];
                j++;
            }
            // i>= 0 j=n - 1 +1 则全是负数
            else if(j == n){//全是负数
                ans[index] = nums[i] * nums[i];//负数越后面 平方后越小 所以是从i开始-- 从后面小的开始平方
                --i;
            }
            //普通情况，不是全正，也不是全负
            //比较 负数 和 正数平方后的大小， 注意负数的从i开始，然后-- 正数从j开始++
            //注意i-- j++
            else if (nums[i] * nums[i] < nums[j] * nums[j]) {
                ans[index] = nums[i] * nums[i];
                --i;
            } else {
                ans[index] = nums[j] * nums[j];
                ++j;
            }

            //每插入一个数记得index++;
            ++index;
        }
        return  ans;
    }
}*/

    // 对法二进行优化，思想差不多 时空也没有改变 不过实现起来简单很多，代码可读性更好。
    // 考虑到前面平方后递减，后面平方递增
    //设置两个指针p q分别指向负数的开头 和 非负数的末尾
    //逆序地插入数组中 当p > q时就可以停止了

    //连找分界线都省去了，直接前后指针一个0，一个n-1，比较一下平方后谁大，谁插入ans数组
    //这里的index从n-1开始，然后向前推。相当于先插入大的。
    public int[] sortedSquares(int[] nums){
        int n = nums.length;
        int[] ans = new int[n];
        //注意不要在这里定义多个int,只要写一个int就行了
        for (int i = 0,j = n -1,pos = n-1; i<=j;) {
            if(nums[i] * nums[i] > nums[j] * nums[j])
            {
                ans[pos] = nums[i] * nums[i];
                i++;
            }
            else{
                ans[pos] = nums[j] *nums[j];
                j--;
            }
            pos--;
        }
        return ans;
    }
}
